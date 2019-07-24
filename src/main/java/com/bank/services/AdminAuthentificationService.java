package com.bank.services;

import com.bank.dao.AdministratorDAO;
import com.bank.dao.DAOFactory;
import com.bank.dao.transaction.TransactionManager;
import com.bank.dto.Administrator;
import com.bank.exception.TransactionException;

public class AdminAuthentificationService {
    private static AdministratorDAO administratorDAO = DAOFactory.getAdministratorDAO();

    public static Administrator getAdminByLoginAndPassword(String login, String password) {
        Administrator admin = null;
        try {
            TransactionManager.beginTransaction();
            admin = administratorDAO.getAdministratorByLogin(login);
        } catch (TransactionException e) {
            TransactionManager.rollBackTransaction();
            throw e;
        } finally {
            TransactionManager.commitTransaction();
        }
        if (admin != null) {
            if (admin.getPassword().equals(password)) {
                return admin;
            }
        }
        return null;
    }
}
