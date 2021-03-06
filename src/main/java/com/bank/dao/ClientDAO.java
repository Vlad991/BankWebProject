package com.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bank.dao.transaction.TransactionManager;
import com.bank.dao.transaction.WrapConnection;
import com.bank.dao.transaction.pool.ConnectionPool;
import com.bank.dto.Client;
import com.bank.dto.Date;

public class ClientDAO {
//	private ConnectionPool connectionPool;
	private static WrapConnection connection;

	public ClientDAO() {
//		connectionPool = ConnectionPool.getConnectionPool();
//		connection = connectionPool.getConnection();
//		connection = TransactionManager.getConnection();
	}

//	public void returnConnectionInPool() {
//		connectionPool.returnConnection(connection);
//	}

	public PreparedStatement getPreparedStatement(String sql) {
		connection = TransactionManager.getConnection();
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ps;
	}

	public void closePreparedStatement(PreparedStatement ps) {
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public List<Long> getClientIds() {
		List<Long> clientIds = new ArrayList<>();
		PreparedStatement ps = null;
		try {
			ps = getPreparedStatement("select id from clients");
			ResultSet res = ps.executeQuery();
			while (res.next()) {
				clientIds.add(res.getLong("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		finally {
//			closePreparedStatement(ps);
//		}
		return clientIds;
	}

	public Client getClientById(Long id) {
		Client client = null;
		PreparedStatement ps = null;
		try {
			ps = getPreparedStatement("select login, name," +
					" surname, birthday, address_id, email, phone, password from clients where id = ?");
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			client = new Client(id, rs.getString("login"),
					rs.getString("name"),
					rs.getString("surname"),
					new Date(rs.getString("birthday")),
					rs.getLong("address_id"),
					rs.getString("email"),
					rs.getString("phone"),
					rs.getString("password"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return client;
	}

	public List<Client> getAllClients() {
		List<Client> clientList = new ArrayList<>();
		PreparedStatement ps = null;
		try {
			ps = getPreparedStatement("select * from clients");
			ResultSet res = ps.executeQuery();
			while (res.next()) {
				clientList.add(new Client(res.getLong("id"),
						res.getString("login"),
						res.getString("name"),
						res.getString("surname"),
						new Date(res.getString("birthday")),
						res.getLong("address_id"),
						res.getString("email"),
						res.getString("phone"),
						res.getString("password")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clientList;
	}

	public Client getClientByLogin(String login) {
		Client client = null;
		PreparedStatement ps = null;
		try {
			ps = getPreparedStatement("select id, name," +
					" surname, birthday, address_id, email, phone, password from clients where login = ?");
			ps.setString(1, login);
			ResultSet rs = ps.executeQuery();
			rs.next();
			client = new Client(rs.getLong("id"),
					login,
					rs.getString("name"),
					rs.getString("surname"),
					new Date(rs.getString("birthday")),
					rs.getLong("address_id"),
					rs.getString("email"),
					rs.getString("phone"),
					rs.getString("password"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return client;
	}

	public void addClient(Client client) {
		PreparedStatement ps = null;
		try {
			ps = getPreparedStatement("insert into clients " +
					"(id, " +
					"login, " +
					"name, " +
					"surname, " +
					"birthday, " +
					"address_id, " +
					"email, " +
					"phone, " +
					"password) " +
					"values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setLong(1, client.getId());
			ps.setString(2, client.getLogin());
			ps.setString(3, client.getName());
			ps.setString(4, client.getSurname());
			ps.setString(5, client.getBirthday().toString());
			ps.setLong(6, client.getAddressId());
			ps.setString(7, client.getEmail());
			ps.setString(8, client.getPhone());
			ps.setString(9, client.getPassword());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setClient(Client client) {
		PreparedStatement ps = null;
		try {
			ps = getPreparedStatement("update clients set " +
					"id = ?, " +
					"login = ?, " +
					"name = ?, " +
					"surname = ?, " +
					"birthday = ?, " +
					"address_id = ?, " +
					"email = ?, " +
					"phone = ?, " +
					"password = ? " +
					"where id = ?");
			ps.setLong(1, client.getId());
			ps.setString(2, client.getLogin());
			ps.setString(3, client.getName());
			ps.setString(4, client.getSurname());
			ps.setString(5, client.getBirthday().toString());
			ps.setLong(6, client.getAddressId());
			ps.setString(7, client.getEmail());
			ps.setString(8, client.getPhone());
			ps.setString(9, client.getPassword());
			ps.setLong(10, client.getId());

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void removeClient(Long id) {
		PreparedStatement ps = null;
		try {
			ps = getPreparedStatement("delete from clients where id = ?");
			ps.setLong(1, id);

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getClientPass(Long id) {
		return getClientById(id).getPassword();
	}

	public void setClientPass(Long id, String pass) {
		PreparedStatement ps = null;
		try {
			ps = getPreparedStatement("update clients set pass = ? where id = ?");
			ps.setString(1, pass);
			ps.setLong(2, id);

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean clientIsExist(String login, String pass) {
		if ((getClientByLogin(login) != null) && ((getClientPass(getClientByLogin(login).getId())).equals(pass))) {
			return true;
		} else {
			return false;
		}
	}

	public void closeConnection() {
	}
	
}
