package com.bank.services;

import com.bank.dao.CreditCardDAO;
import com.bank.dao.DAOFactory;
import com.bank.dao.transaction.TransactionManager;
import com.bank.dto.CreditCard;
import com.bank.exception.TransactionException;

public class CreditCardAuthentificationService {
    private static CreditCardDAO creditCardDAO = DAOFactory.getCreditCardDAO();

    public static CreditCard getCreditCardByIdAndPin(Long id, int pin) {
        CreditCard card = null;
        try {
            TransactionManager.beginTransaction();
            card = creditCardDAO.getCreditCardById(id);
        } catch (TransactionException e) {
            TransactionManager.rollBackTransaction();
            throw e;
        } finally {
            TransactionManager.commitTransaction();
        }
        if (card != null) {
            if (card.getPin() == pin) {
                return card;
            }
        }
        return null;
    }
}
