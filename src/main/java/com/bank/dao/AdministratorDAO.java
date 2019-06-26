package com.bank.dao;

import com.bank.dao.transaction.TransactionManager;
import com.bank.dao.transaction.WrapConnection;
import com.bank.dto.Administrator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class AdministratorDAO {

	private WrapConnection connection;

	public AdministratorDAO() {
//		connectionPool = ConnectionPool.getConnectionPool();
//		connection = connectionPool.getConnection();
		connection = TransactionManager.getConnection();
	}

//	public void returnConnectionInPool() {
//		connectionPool.returnConnection(connection);
//	}

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

	public List<Integer> getAdministratorIds() {
		List<Integer> adminIds = new ArrayList<>();
		PreparedStatement ps = null;
		try {
			ps = getPreparedStatement("select id from administrators");
			ResultSet res = ps.executeQuery();
			while (res.next()) {
				adminIds.add(res.getInt("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closePreparedStatement(ps);
		}
		return adminIds;
	}

	public Administrator getAdministratorById(int id) {
		Administrator admin = null;
		PreparedStatement ps = null;
		try {
			ps = getPreparedStatement("select login, name," +
					" surname, password from administrators where id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			admin = new Administrator(id, rs.getString("login"),
					rs.getString("name"),
					rs.getString("surname"),
					rs.getString("password"));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closePreparedStatement(ps);
		}
		return admin;
	}

	public Administrator getAdministratorByLogin(String login) {
		Administrator admin = null;
		PreparedStatement ps = null;
		try {
			ps = getPreparedStatement("select id, name," +
					" surname, password from administrators where login = ?");
			ps.setString(1, login);
			ResultSet rs = ps.executeQuery();
			rs.next();
			admin = new Administrator(rs.getInt("id"),
					login,
					rs.getString("name"),
					rs.getString("surname"),
					rs.getString("password"));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closePreparedStatement(ps);
		}
		return admin;
	}

	public void addAdministrator(Administrator admin) {
		PreparedStatement ps = null;
		try {
			ps = getPreparedStatement("insert into administrators " +
					"(id, " +
					"login, " +
					"name, " +
					"surname, " +
					"password) " +
					"values (?, ?, ?, ?, ?)");
			ps.setInt(1, admin.getId());
			ps.setString(2, admin.getLogin());
			ps.setString(3, admin.getName());
			ps.setString(4, admin.getSurname());
			ps.setString(5, admin.getPassword());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closePreparedStatement(ps);
		}
	}

	public void setAdministrator(Administrator admin) {
		PreparedStatement ps = null;
		try {
			ps = getPreparedStatement("update administrators set " +
					"id = ?, " +
					"login = ?, " +
					"name = ?, " +
					"surname = ?, " +
					"password = ? " +
					"where id = ?");
			ps.setInt(1, admin.getId());
			ps.setString(2, admin.getLogin());
			ps.setString(3, admin.getName());
			ps.setString(4, admin.getSurname());
			ps.setString(5, admin.getPassword());
			ps.setInt(6, admin.getId());

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closePreparedStatement(ps);
		}
	}

	public void removeAdministrator(int id) {
		PreparedStatement ps = null;
		try {
			ps = getPreparedStatement("delete from administrators where id = ?");
			ps.setInt(1, id);

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closePreparedStatement(ps);
		}
	}
	
	public String getAdministratorPass(int id) {
		return getAdministratorById(id).getPassword();
	}
	
	public void setAdministratorPass(int id, String pass) {
		PreparedStatement ps = null;
		try {
			ps = getPreparedStatement("update administrators set pass = ? where id = ?");
			ps.setString(1, pass);
			ps.setInt(2, id);

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closePreparedStatement(ps);
		}
	}
	
	public boolean adminIsExist(String login, String pass) {
		if ((getAdministratorByLogin(login) != null) && ((getAdministratorPass(getAdministratorByLogin(login).getId())).equals(pass))) {
			return true;
		} else {
			return false;
		}
	}
	
	public void closeConnection() {
	}
}
