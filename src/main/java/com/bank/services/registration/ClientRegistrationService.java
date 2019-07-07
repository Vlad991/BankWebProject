package com.bank.services.registration;

import com.bank.dao.AddressDAO;
import com.bank.dao.ClientDAO;
import com.bank.dao.DAOFactory;
import com.bank.dao.transaction.TransactionManager;
import com.bank.dto.Address;
import com.bank.dto.Client;
import com.bank.dto.CreditCard;
import com.bank.exception.TransactionException;
import com.bank.services.id_generator.IdGenerator;

public class ClientRegistrationService {
    private static ClientDAO clientDAO = DAOFactory.getClientDAO();

    public static void registerClientWithNewCard(Client client, Address address) {
//        if (!clientAlreadyRegistered(client)) {
//            Long addressId = AddressRegistrationService.registerClientAddress(address);
//            client.setId(IdGenerator.getClientId());
//            client.setAddressId(addressId);
//
//            try {
//                TransactionManager.beginTransaction();
//                clientDAO.addClient(client);
//            } catch (TransactionException e) {
//                TransactionManager.rollBackTransaction();
//                throw e;
//            } finally {
//                TransactionManager.commitTransaction();
//            }
//
//            CardRegistrationSrevice.registerNewCreditCardForClient(client);
//        }

    }
}
