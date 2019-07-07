package com.bank.dao;

public class DAOFactory {
    private static ClientDAO clientDAO = new ClientDAO();
    private static AddressDAO addressDAO = new AddressDAO();
    private static AdministratorDAO administratorDAO = new AdministratorDAO();
    private static CreditCardDAO creditCardDAO = new CreditCardDAO();
    private static UserDAO userDAO = new UserDAO();

    public static ClientDAO getClientDAO() {
        return clientDAO;
    }

    public static AddressDAO getAddressDAO() {
        return addressDAO;
    }

    public static AdministratorDAO getAdministratorDAO() {
        return administratorDAO;
    }

    public static CreditCardDAO getCreditCardDAO() {
        return creditCardDAO;
    }

    public static UserDAO getUserDAO() {
        return userDAO;
    }
}
