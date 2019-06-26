package com.bank.dao.transaction.pool;

import com.bank.exception.ConnectionPoolIsEmptyException;
import lombok.Getter;
import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.*;

public class ConnectionPool {
    private BlockingQueue<ConnectionHolder> connectionsPool = new LinkedBlockingDeque<>();
    @Getter
    private int poolSize;
    private long connectionTimeoutInThePool;
    private int usedConnections;
    private Properties dbProperties;

    public ConnectionPool(int poolSize, long connectionTimeoutInSecond) {
        this.poolSize = poolSize;
        this.connectionTimeoutInThePool = connectionTimeoutInSecond;
        try {
            createConnectionAndFillThePool();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        ConnectionUsageMonitoring connectionUsageMonitoring = new ConnectionUsageMonitoring();
        connectionUsageMonitoring.run();
    }

    int getCurrentPoolSize() {
        return connectionsPool.size();
    }

    private void returnConnectionToThePool(Connection connection) {
        connectionsPool.add(new ConnectionHolder(connection, new Date()));
    }

    @SneakyThrows
    public Connection getConnection() {
        if (poolSize == usedConnections) {
            throw new ConnectionPoolIsEmptyException("Connection pool is empty!");// no unused connections
        } else if (connectionsPool.isEmpty() && usedConnections < poolSize) {
            usedConnections++;
            return createAndGetConnection();
        } else {
            final Connection connection = getPendingConnection();
            return connection.isValid(0) ? connection : createAndGetConnection();
        }
    }

//    private JdbcConnection getJdbcConnection() throws ClassNotFoundException, SQLException {
//        return new JdbcConnection(createAndGetConnection(), this);
//    }

    private Connection getPendingConnection() throws InterruptedException {
        final ConnectionHolder connectionHolder = connectionsPool.poll(connectionTimeoutInThePool, TimeUnit.SECONDS);
        usedConnections++;
        return connectionHolder.getConnection();
    }

    public void createConnectionAndFillThePool() throws SQLException, ClassNotFoundException {
        for (int i = 0; i < poolSize; i++) {
            final Connection connection = createAndGetConnection();
            Date connectionCreationTime = new Date();
            connectionsPool.add(new ConnectionHolder(connection, connectionCreationTime));
        }
    }

    private Connection createAndGetConnection() throws ClassNotFoundException, SQLException {
        loadPropertyFile();
        String driver = dbProperties.getProperty("driver");
        Class.forName(driver);
        String url = dbProperties.getProperty("url");
        Properties properties = setAndGetDataBaseProperties();
        return getConnectionFromDriverManager(url, properties);
    }

    private void loadPropertyFile() {
        try (FileInputStream in = new FileInputStream("/Users/kuzmavladislavvladimirovic/Documents/2019/java3/BankWebProject/src/main/resources/db.properties")) {
            dbProperties = new Properties();
            dbProperties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnectionFromDriverManager(String url, Properties properties) throws SQLException {
        return DriverManager.getConnection(url, properties);
    }

    private Properties setAndGetDataBaseProperties() {
        Properties properties = new Properties();
        String username = dbProperties.getProperty("username");
        String password = dbProperties.getProperty("password");
        properties.setProperty("user", username);
        properties.setProperty("password", password);
        properties.setProperty("ssl", "false");
        return properties;
    }

    void close(Connection connection) throws SQLException {
        if (connection.isValid(0)) {
            closeValidConnection(connection);
        } else {
            connection.close();
        }
    }

    private void closeValidConnection(Connection connection) {
        if (getCurrentPoolSize() == getPoolSize()) {
            closeConnectionIfPoolFull(connection);
        }
        putUserConnectionToThePool(connection);
    }

    private void closeConnectionIfPoolFull(Connection connection) {
        usedConnections--;
        returnConnectionToThePool(connection);
    }

    private void putUserConnectionToThePool(Connection connection) {
        usedConnections--;
        returnConnectionToThePool(connection);
    }

    private class ConnectionUsageMonitoring {
        private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        void run() {
            executorService.schedule(this::cleanUpConnectionPool, 1000, TimeUnit.MILLISECONDS);
        }

        private void cleanUpConnectionPool() {
            doWork();
            executorService.schedule(this::cleanUpConnectionPool, 1000, TimeUnit.MICROSECONDS);
        }

        private void doWork() {
            try {
                startMonitoringConnectionUsage();
            } catch(Throwable e) {
                e.printStackTrace();
            }
        }

        private void startMonitoringConnectionUsage() throws SQLException {
            final Iterator<ConnectionHolder> iterator = connectionsPool.iterator();
            closeUnusedConnection(iterator);
        }

        private void closeUnusedConnection(Iterator<ConnectionHolder> iterator) throws SQLException {
            if (iterator.hasNext()) {
                final ConnectionHolder connectionHolder = iterator.next();
                if (connectionTimeout(connectionHolder.getLastAccessTime())){
                    iterator.remove();
                    connectionHolder.getConnection().close();
                }
            }
        }

        private boolean connectionTimeout(Date lastAccessTime){
            final long l = Duration.between(lastAccessTime.toInstant(), Instant.now()).toMillis();
            return l > 2000;
        }
    }

}
