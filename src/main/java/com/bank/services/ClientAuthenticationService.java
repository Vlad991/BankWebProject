package com.bank.services;

import com.bank.dao.AddressDAO;
import com.bank.dao.ClientDAO;
import com.bank.dao.CreditCardDAO;
import com.bank.dao.DAOFactory;
import com.bank.dao.transaction.TransactionManager;
import com.bank.dto.Address;
import com.bank.dto.Client;
import com.bank.dto.CreditCard;
import com.bank.exception.TransactionException;

import java.util.ArrayList;
import java.util.List;

public class ClientAuthenticationService {
    private static ClientDAO clientDAO = DAOFactory.getClientDAO();
    private static AddressDAO addressDAO = DAOFactory.getAddressDAO();
    private static CreditCardDAO creditCardDAO = DAOFactory.getCreditCardDAO();

    public static Client getClientByLoginAndPassword(String login, String password) {
        Client client = null;
        try {
            TransactionManager.beginTransaction();
            client = clientDAO.getClientByLogin(login);
        } catch (TransactionException e) {
            TransactionManager.rollBackTransaction();
            throw e;
        } finally {
            TransactionManager.commitTransaction();
        }
        if (client != null) {
            if (client.getPassword().equals(password)) {
                return client;
            }
        }
        return null;
    }

    public static Address getClientAddressByLoginAndPassword(String login, String password) {
        Client client = getClientByLoginAndPassword(login, password);
        if (client == null) {
            return null;
        }
        Address address = null;
        try {
            TransactionManager.beginTransaction();
            address = addressDAO.getAddressById(client.getAddressId());
        } catch (TransactionException e) {
            TransactionManager.rollBackTransaction();
            throw e;
        } finally {
            TransactionManager.commitTransaction();
        }
        return address;
    }

    public static List<CreditCard> getClientCreditCardListByLoginAndPassword(String login, String password) {
        Client client = getClientByLoginAndPassword(login, password);
        if (client == null) {
            return null;
        }
        List<CreditCard> creditCardList = new ArrayList<>();
        try {
            TransactionManager.beginTransaction();
            creditCardList = creditCardDAO.getCreditCardsByClientId(client.getId());
        } catch (TransactionException e) {
            TransactionManager.rollBackTransaction();
            throw e;
        } finally {
            TransactionManager.commitTransaction();
        }
        return creditCardList;
    }

}
