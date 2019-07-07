package com.bank.dao.transaction;

import com.bank.dao.transaction.pool.ConnectionPool;
import com.bank.exception.TransactionException;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager {
    private static ThreadLocal<WrapConnection> connections = new ThreadLocal<>();
    private static ConnectionPool dataSource;

    static {
        dataSource = new ConnectionPool(10, 30);
    }

    private TransactionManager() {
    }

    public static void beginTransaction() {
        if (connections.get() != null) {
            throw new TransactionException();
        }
        try {
            Connection connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            WrapConnection wrapConnection = new WrapConnection(connection);
            connections.set(wrapConnection);
        } catch (SQLException ex) {
            throw new TransactionException(ex);
        }
    }

    public static void rollBackTransaction() {
        if (connections.get() == null) {
            throw new TransactionException();
        }
        try {
            connections.get().rollback();
        } catch (SQLException e) {
            throw new TransactionException(e);
        }
    }

    public static void commitTransaction() {
        if (connections.get() == null) {
            throw new TransactionException();
        }
        try {
            WrapConnection connection = connections.get();
            connection.commit();
            Connection realConnection = connection.getRealConnection();
            realConnection.close();
            connections.set(null);
        } catch (SQLException e) {
            throw new TransactionException(e);
        }
    }

    public static WrapConnection getConnection() {
        WrapConnection wrapConnection = connections.get();
        if (wrapConnection == null) {
            throw new TransactionException();
        }
        return wrapConnection;
    }
}
