package com.bank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bank.dto.Client;
import com.bank.dto.CreditCard;

public class ClientDAO {
	private Connection connection;
	private ConnectionPool connectionPool;
	private CreditCardDAO creditCardDAO;

	public ClientDAO() {
		connectionPool = ConnectionPool.getConnectionPool();
		connection = connectionPool.getConnection();
	}

	public void returnConnectionInPool() {
		connectionPool.returnConnection(connection);
	}

	public PreparedStatement getPreparedStatement(String sql) {
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

//	private Connection getConnection() throws Exception {
//		Class.forName("com.mysql.cj.jdbc.Driver");
//		return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/accountDB", "root", "123root@");
//	}

	public List<Long> getClientIds() throws Exception {
		List<Long> clientIds = new ArrayList<>();
		PreparedStatement ps = getPreparedStatement().executeQuery("select id from clients");
		ResultSet res = st.executeQuery();
		while (res.next()) {
			clientIds.add(res.getLong("id"));
		}
		res.close();
		return clientIds;
	}

	public Client getClientById(Long id) throws Exception {
		PreparedStatement st = connection
				.prepareStatement("select login, name, surname," +
						" email, phone, country, city, address," +
						" date_of_birth, password from clients where id = ?");
		st.setLong(1, id);
		ResultSet rs = st.executeQuery();
		rs.next();
		Client client = new Client(id, rs.getString("login"),
				rs.getString("name"),
				rs.getString("surname"),
				rs.getString("email"),
				rs.getString("phone"),
				rs.getString("country"),
				rs.getString("city"),
				rs.getString("address"),
				rs.getString("date_of_birth"),
				rs.getString("password"));
		rs.close();
		return client;
	}

	public Client getClientByLogin(String login) throws Exception {
		PreparedStatement st = connection
				.prepareStatement("select id, name, surname," +
						" email, phone, country, city, address," +
						" date_of_birth, password from clients where login = ?");
		st.setString(1, login);
		ResultSet rs = st.executeQuery();
		rs.next();
		Client client = new Client(rs.getLong("id"),
				login,
				rs.getString("name"),
				rs.getString("surname"),
				rs.getString("email"),
				rs.getString("phone"),
				rs.getString("country"),
				rs.getString("city"),
				rs.getString("address"),
				rs.getString("date_of_birth"),
				rs.getString("password"));
		rs.close();
		return client;
	}

	public void addNewClient(Client client) throws Exception {
		PreparedStatement st = connection
				.prepareStatement("insert into clients (" +
						"id, " +
						"login, " +
						"name, " +
						"surname, " +
						"email, " +
						"phone, " +
						"country, " +
						"city, " +
						"address, " +
						"date_of_birth, " +
						"password) " +
						"values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		st.setLong(1, client.getId());
		st.setString(2, client.getLogin());
		st.setString(3, client.getName());
		st.setString(4, client.getSurname());
		st.setString(5, client.getEmail());
		st.setString(6, client.getPhone());
		st.setString(7, client.getCountry());
		st.setString(8, client.getCity());
		st.setString(9, client.getAddress());
		st.setString(10, client.getDateOfBirth());
		st.setString(11, client.getPassword());

		st.executeUpdate();
	}

	public void setClient(Client client) throws Exception {
		PreparedStatement st = connection
				.prepareStatement("update clients set " +
						"login = ?, " +
						"name = ?, " +
						"surname = ?, " +
						"email = ?, " +
						"phone = ?, " +
						"country = ?, " +
						"city = ?, " +
						"address = ?, " +
						"date_of_birth = ?, " +
						"password = ? " +
						"where id = ?");
		st.setString(1, client.getLogin());
		st.setString(2, client.getName());
		st.setString(3, client.getSurname());
		st.setString(4, client.getEmail());
		st.setString(5, client.getPhone());
		st.setString(6, client.getCountry());
		st.setString(7, client.getCity());
		st.setString(8, client.getAddress());
		st.setString(9, client.getDateOfBirth());
		st.setString(10, client.getPassword());
		st.setLong(11, client.getId());

		st.executeUpdate();
	}

	public void removeClient(Long id) throws Exception {
		PreparedStatement st = connection.prepareStatement("delete from clients where id = ?");
		st.setLong(1, id);

		st.executeUpdate();
	}

	public String getClientPass(int id) throws Exception {
		String pass = null;
		Connection con = getConnection();
		PreparedStatement st = con.prepareStatement("select pass " + "from clients " + "where id_cl = ?");
		st.setInt(1, id);
		ResultSet rs = st.executeQuery();
		while (rs.next()) {
			pass = rs.getString("pass");
		}
		rs.close();
		return pass;
	}

	public void setClientPass(int id, String pass) throws Exception {
		Connection con = getConnection();
		PreparedStatement st = con.prepareStatement("update clients " + "set pass = " + pass + " where id_cl = ?");
		st.setInt(1, id);

		st.executeUpdate();
	}
	
	public boolean clientIsExist(String name, String pass) throws Exception {
		if ((getClientByName(name) != null) && ((getClientPass(getClientByName(name).getId())).equals(pass))) {
			return true;
		} else {
			return false;
		}
	}
	
	public void closeConnection() {
	}
	
}
