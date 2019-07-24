package com.bank.services;

import com.bank.dao.ClientDAO;
import com.bank.dao.DAOFactory;
import com.bank.dao.transaction.TransactionManager;
import com.bank.dto.Client;
import com.bank.exception.TransactionException;

import java.util.ArrayList;
import java.util.List;

public class ClientListService {
    private static ClientDAO clientDAO = DAOFactory.getClientDAO();

    public static List<Client> getAllClinets() {
        List<Client> clientList = new ArrayList<>(); //todo
        try {
            TransactionManager.beginTransaction();
            clientList = clientDAO.getAllClients();
        } catch (TransactionException e) {
            TransactionManager.rollBackTransaction();
            throw e;
        } finally {
            TransactionManager.commitTransaction();
        }
        return clientList;
    }
}
