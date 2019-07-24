package com.bank.services.id_generator;

import com.bank.dao.*;
import com.bank.dao.transaction.TransactionManager;
import com.bank.exception.TransactionException;

import java.util.List;

public class IdGenerator {
    private static ClientDAO clientDAO = DAOFactory.getClientDAO();
    private static AddressDAO addressDAO = DAOFactory.getAddressDAO();
    private static AdministratorDAO administratorDAO = DAOFactory.getAdministratorDAO();
    private static CreditCardDAO creditCardDAO = DAOFactory.getCreditCardDAO();

    public static Long getClientId() {
        Long clientId = 1l;
        try {
            TransactionManager.beginTransaction();
            List<Long> clientIds = clientDAO.getClientIds();
            for (int i = 1; i <= clientIds.size() + 2; i++) {
                if (clientIds.indexOf(new Long(i)) == -1) {
                    clientId = new Long(i);
                    break;
                }
            }
        } catch (TransactionException e) {
            TransactionManager.rollBackTransaction();
            throw e;
        } finally {
            TransactionManager.commitTransaction();
        }
        return clientId;
    }

    public static Long getAddressId() {
        Long addressId = 1l;
        try {
            TransactionManager.beginTransaction();
            List<Long> addressIds = addressDAO.getAddressIds();
            for (int i = 1; i <= addressIds.size() + 2; i++) {
                if (addressIds.indexOf(new Long(i)) == -1) {
                    addressId = new Long(i);
                    break;
                }
            }
        } catch (TransactionException e) {
            TransactionManager.rollBackTransaction();
            throw e;
        } finally {
            TransactionManager.commitTransaction();
        }
        return addressId;
    }

    public static Integer getAdministratorId() {
        int adminId = 1;
        try {
            TransactionManager.beginTransaction();
            List<Integer> adminIds = administratorDAO.getAdministratorIds();
            for (int i = 1; i <= adminIds.size() + 2; i++) {
                if (adminIds.indexOf(i) == -1) {
                    adminId = i;
                    break;
                }
            }
        } catch (TransactionException e) {
            TransactionManager.rollBackTransaction();
            throw e;
        } finally {
            TransactionManager.commitTransaction();
        }
        return adminId;
    }

    public static Long getCreditCardId() {
        Long cardId = 1414000000000000l;
        try {
            TransactionManager.beginTransaction();
            List<Long> cardIds = creditCardDAO.getCreditCardIds();
            for (int i = 1; i <= cardIds.size() + 2; i++) {
                if (cardIds.indexOf(cardId + i) == -1) {
                    cardId = cardId + i;
                    break;
                }
            }
        } catch (TransactionException e) {
            TransactionManager.rollBackTransaction();
            throw e;
        } finally {
            TransactionManager.commitTransaction();
        }
        return cardId;
    }

}
