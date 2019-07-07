package com.bank.dao;

import com.bank.dao.transaction.TransactionManager;
import com.bank.dao.transaction.WrapConnection;
import com.bank.dto.CreditCard;
import com.bank.dto.CreditCardStatus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CreditCardDAO {
    private WrapConnection connection;

    public CreditCardDAO() {
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

    public List<Long> getCreditCardIds() {
        List<Long> creditcardIds = new ArrayList<>();
        PreparedStatement ps = null;
        try {
            ps = getPreparedStatement("select id from creditcards");
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                creditcardIds.add(res.getLong("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return creditcardIds;
    }

    public CreditCard getCreditCardById(Long id) {
        CreditCard creditCard = null;
        PreparedStatement ps = null;
        try {
            ps = getPreparedStatement("select date, client_id," +
                    " code, pin, sum, status from creditcards where id = ?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            creditCard = new CreditCard(id, rs.getString("date"),
                    rs.getLong("client_id"),
                    rs.getInt("code"),
                    rs.getInt("pin"),
                    rs.getLong("sum"),
                    CreditCardStatus.valueOf(rs.getString("status")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return creditCard;
    }

    public CreditCard getCreditCardByClientId(Long clientId) {
        CreditCard creditCard = null;
        PreparedStatement ps = null;
        try {
            ps = getPreparedStatement("select id, date," +
                    " code, pin, sum, phone, status from creditcards where client_id = ?");
            ps.setLong(1, clientId);
            ResultSet rs = ps.executeQuery();
            rs.next();
            creditCard = new CreditCard(rs.getLong("id"),
                    rs.getString("date"),
                    clientId,
                    rs.getInt("code"),
                    rs.getInt("pin"),
                    rs.getLong("sum"),
                    CreditCardStatus.valueOf(rs.getString("status")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return creditCard;
    }

    public void addCreditCard(CreditCard creditCard) {
        PreparedStatement ps = null;
        try {
            ps = getPreparedStatement("insert into creditcards " +
                    "(id, " +
                    "date, " +
                    "client_id, " +
                    "code, " +
                    "pin, " +
                    "sum, " +
                    "status) " +
                    "values (?, ?, ?, ?, ?, ?, ?)");
            ps.setLong(1, creditCard.getId());
            ps.setString(2, creditCard.getDate());
            ps.setLong(3, creditCard.getClientId());
            ps.setInt(4, creditCard.getCode());
            ps.setInt(5, creditCard.getPin());
            ps.setLong(6, creditCard.getSum());
            ps.setString(7, creditCard.getStatus().getStatus());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setCreditCard(CreditCard creditCard) {
        PreparedStatement ps = null;
        try {
            ps = getPreparedStatement("update creditcards set " +
                    "(id, " +
                    "date, " +
                    "client_id, " +
                    "code, " +
                    "pin, " +
                    "sum, " +
                    "status) " +
                    "values (?, ?, ?, ?, ?, ?, ?) " +
                    "where id = ?");
            ps.setLong(1, creditCard.getId());
            ps.setString(2, creditCard.getDate());
            ps.setLong(3, creditCard.getClientId());
            ps.setInt(4, creditCard.getCode());
            ps.setInt(5, creditCard.getPin());
            ps.setLong(6, creditCard.getSum());
            ps.setString(7, creditCard.getStatus().getStatus());
            ps.setLong(8, creditCard.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeCreditCard(Long id) {
        PreparedStatement ps = null;
        try {
            ps = getPreparedStatement("delete from creditcards where id = ?");
            ps.setLong(1, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void blockCreditCard(Long id) {
        PreparedStatement ps = null;
        CreditCard creditCard = getCreditCardById(id);
        try {
            ps = getPreparedStatement("update creditcards set " +
                    "(id, " +
                    "date, " +
                    "client_id, " +
                    "code, " +
                    "pin, " +
                    "sum, " +
                    "status) " +
                    "values (?, ?, ?, ?, ?, ?, ?) " +
                    "where id = ?");
            ps.setLong(1, creditCard.getId());
            ps.setString(2, creditCard.getDate());
            ps.setLong(3, creditCard.getClientId());
            ps.setInt(4, creditCard.getCode());
            ps.setInt(5, creditCard.getPin());
            ps.setLong(6, creditCard.getSum());
            ps.setString(7, CreditCardStatus.BLOCKED.getStatus());
            ps.setLong(8, creditCard.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public boolean creditCardIsExist(Long id) {
        if (getCreditCardById(id) != null) {
            return true;
        } else {
            return false;
        }
    }

    public void closeConnection() {
    }

}
