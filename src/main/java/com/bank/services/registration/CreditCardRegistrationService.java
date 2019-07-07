package com.bank.services.registration;

import com.bank.dao.CreditCardDAO;
import com.bank.dao.DAOFactory;
import com.bank.dao.transaction.TransactionManager;
import com.bank.dto.Client;
import com.bank.dto.CreditCard;
import com.bank.dto.CreditCardStatus;
import com.bank.exception.TransactionException;
import com.bank.services.id_generator.IdGenerator;

import java.util.Date;

public class CreditCardRegistrationService {
    private static CreditCardDAO creditCardDAO = DAOFactory.getCreditCardDAO();

    public static void registerNewCreditCardForClient(Client client) {
        Long cardId = IdGenerator.getCreditCardId();
        CreditCard newCreditCard = new CreditCard(cardId,
                getCardDate(),
                client.getId(),
                getCardCode(),
                getCardPin(),
                0l,
                CreditCardStatus.OPEN);
        try {
            TransactionManager.beginTransaction();
            creditCardDAO.addCreditCard(newCreditCard);
        } catch (TransactionException e) {
            TransactionManager.rollBackTransaction();
            throw e;
        } finally {
            TransactionManager.commitTransaction();
        }
    }

    public static String getCardDate() {
        Date currentDate = new Date();
        return (currentDate.getMonth() + 1) + "/" + (currentDate.getYear() + 1900 + 5);
    }

    private static int getCardCode() {
        return (int) (100 + Math.random()*899);
    }

    private static int getCardPin() {
        return (int) (1000 + Math.random()*8999);
    }
}
