package com.bank.services;

import com.bank.dao.ClientDAO;
import com.bank.dao.CreditCardDAO;
import com.bank.dao.DAOFactory;
import com.bank.dao.transaction.TransactionManager;
import com.bank.dto.CreditCard;
import com.bank.exception.TransactionException;

public class MoneyTransferService {
    private static CreditCardDAO creditCardDAO = DAOFactory.getCreditCardDAO();
    private static ClientDAO clientDAO = DAOFactory.getClientDAO();

    public static TransactionResult transferMoneyFromAccountToAnotherAccount
            (Long senderCardId,
             Long recipientCardId, Long sum) {
        try {
            TransactionManager.beginTransaction();
            CreditCard senderCard = creditCardDAO.getCreditCardById(senderCardId);
            CreditCard recipientCard = creditCardDAO.getCreditCardById(recipientCardId);

            if (senderCard.getSum() < sum) {
                throw new TransactionException();
            }

            senderCard.setSum(senderCard.getSum() - sum);
            recipientCard.setSum(recipientCard.getSum() + sum);

            creditCardDAO.setCreditCard(senderCard);
            creditCardDAO.setCreditCard(recipientCard);
        } catch (TransactionException e) {
            TransactionManager.rollBackTransaction();
            return TransactionResult.FAILED;
        } finally {
            TransactionManager.commitTransaction();
        }
        return TransactionResult.SUCCESSFUL;
    }
}
