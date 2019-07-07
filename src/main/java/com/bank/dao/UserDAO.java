package com.bank.dao;

import com.bank.dao.transaction.TransactionManager;
import com.bank.dao.transaction.WrapConnection;
import com.bank.dto.Role;
import com.bank.dto.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private WrapConnection connection;

    public UserDAO() {
    }

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

    public List<Long> getUserIds() {
        List<Long> userIds = new ArrayList<>();
        PreparedStatement ps = null;
        try {
            ps = getPreparedStatement("select id from users");
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                userIds.add(res.getLong("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userIds;
    }

    public List<Long> getUserRoleIds() {
        List<Long> userRoleIds = new ArrayList<>();
        PreparedStatement ps = null;
        try {
            ps = getPreparedStatement("select role_id from users");
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                userRoleIds.add(res.getLong("role_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userRoleIds;
    }

    public User getUserById(Long id) {
        User user = null;
        PreparedStatement ps = null;
        try {
            ps = getPreparedStatement("select role_id, role from users where id = ?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            user = new User(id,
                    rs.getLong("role_id"),
                    Role.valueOf(rs.getString("role")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User getUserByRoleIdAndRole(Long role_id, Role role) {
        User user = null;
        PreparedStatement ps = null;
        try {
            ps = getPreparedStatement("select id from users where role_id = ? and role = ?");
            ps.setLong(1, role_id);
            ps.setString(2, role.getRole());
            ResultSet rs = ps.executeQuery();
            rs.next();
            user = new User(rs.getLong("id"), role_id, role);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void addUser(User user) {
        PreparedStatement ps = null;
        try {
            ps = getPreparedStatement("insert into users " +
                    "(id, " +
                    "role_id, " +
                    "role) " +
                    "values (?, ?, ?)");
            ps.setLong(1, user.getId());
            ps.setLong(2, user.getRoleId());
            ps.setString(3, user.getRole().getRole());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setUser(User user) {
        PreparedStatement ps = null;
        try {
            ps = getPreparedStatement("update users set " +
                    "id = ?, " +
                    "role_id = ?, " +
                    "role = ? " +
                    "where id = ?");
            ps.setLong(1, user.getId());
            ps.setLong(2, user.getRoleId());
            ps.setString(3, user.getRole().getRole());
            ps.setLong(4, user.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUser(Long id) {
        PreparedStatement ps = null;
        try {
            ps = getPreparedStatement("delete from users where id = ?");
            ps.setLong(1, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean userIsExist(Long role_id, Role role) {
        if (getUserByRoleIdAndRole(role_id, role) != null) {
            return true;
        } else {
            return false;
        }
    }

    public void closeConnection() {
    }
}
