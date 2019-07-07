package com.bank.dao;

public class DAOFactory {
    private static ClientDAO clientDAO = new ClientDAO();
    private static AddressDAO addressDAO = new AddressDAO();

    public static ClientDAO getClientDAO() {
        return clientDAO;
    }

    public static AddressDAO getAddressDAO() {
        return addressDAO;
    }
}
