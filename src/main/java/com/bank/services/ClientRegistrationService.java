package com.bank.services;

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
    private static AddressDAO addressDAO = DAOFactory.getAddressDAO();

    public void registerClientWithNewCard(Client client, Address address) {
        Long addressId = registerClientAddress(address);
        client.setId(IdGenerator.getClientId());
        client.setAddressId(addressId);

        try {
            TransactionManager.beginTransaction();
            clientDAO.addClient(client);
        } catch (TransactionException e) {
            TransactionManager.rollBackTransaction();
            throw e;
        } finally {
            TransactionManager.commitTransaction();
        }

        CardRegistrationSrevice.registerNewCreditCardForClient(client);
    }

    private Long registerClientAddress(Address address) {
        address.setId(IdGenerator.getAddressId());

        try {

        } catch (TransactionException e) {
            TransactionManager.rollBackTransaction();
            throw e;
        } finally {
            TransactionManager.commitTransaction();
        }
    }
}
