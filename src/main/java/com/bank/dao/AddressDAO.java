package com.bank.dao;

import com.bank.dao.transaction.TransactionManager;
import com.bank.dao.transaction.WrapConnection;
import com.bank.dto.Address;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddressDAO {
    private static WrapConnection connection;

    public AddressDAO() {
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

    public List<Long> getAddressIds() {
        List<Long> addressIds = new ArrayList<>();
        PreparedStatement ps = null;
        try {
            ps = getPreparedStatement("select id from addresses");
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                addressIds.add(res.getLong("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return addressIds;
    }

    public Address getAddressById(Long id) {
        Address address = null;
        PreparedStatement ps = null;
        try {
            ps = getPreparedStatement("select country, city," +
                    " street, postcode from addresses where id = ?");
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            address = new Address(id, rs.getString("country"),
                    rs.getString("city"),
                    rs.getString("street"),
                    rs.getInt("postcode"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return address;
    }

    public List<Address> getAllAddresses() {
        List<Address> addressList = new ArrayList<>();
        PreparedStatement ps = null;
        try {
            ps = getPreparedStatement("select * from addresses");
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                addressList.add(new Address(res.getLong("id"),
                        res.getString("country"),
                        res.getString("city"),
                        res.getString("street"),
                        res.getInt("postcode")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return addressList;
    }

    public Address getAddressByPostcode(int postcode) {
        Address address = null;
        PreparedStatement ps = null;
        try {
            ps = getPreparedStatement("select id, country," +
                    " city, street from addresses where postcode = ?");
            ps.setInt(1, postcode);
            ResultSet rs = ps.executeQuery();
            rs.next();
            address = new Address(rs.getLong("id"),
                    rs.getString("country"),
                    rs.getString("city"),
                    rs.getString("street"),
                    postcode);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return address;
    }

    public void addAddress(Address address) {
        PreparedStatement ps = null;
        try {
            ps = getPreparedStatement("insert into addresses " +
                    "(id, " +
                    "country, " +
                    "city, " +
                    "street, " +
                    "postcode) " +
                    "values (?, ?, ?, ?, ?)");
            ps.setLong(1, address.getId());
            ps.setString(2, address.getCountry());
            ps.setString(3, address.getCity());
            ps.setString(4, address.getStreet());
            ps.setInt(5, address.getPostCode());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setAddress(Address address) {
        PreparedStatement ps = null;
        try {
            ps = getPreparedStatement("update addresses set " +
                    "id = ?, " +
                    "country = ?, " +
                    "city = ?, " +
                    "street = ?, " +
                    "postcode = ? " +
                    "where id = ?");
            ps.setLong(1, address.getId());
            ps.setString(2, address.getCountry());
            ps.setString(3, address.getCity());
            ps.setString(4, address.getStreet());
            ps.setInt(5, address.getPostCode());
            ps.setLong(6, address.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeAdministrator(long id) {
        PreparedStatement ps = null;
        try {
            ps = getPreparedStatement("delete from addresses where id = ?");
            ps.setLong(1, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean addressIsExist(Address address) {
        if (address.equals(getAddressById(address.getId()))) {
            return true;
        } else {
            return false;
        }
    }

    public void closeConnection() {
    }
}
