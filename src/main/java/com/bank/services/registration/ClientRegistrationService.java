package com.bank.services.registration;

import com.bank.dao.ClientDAO;
import com.bank.dao.DAOFactory;
import com.bank.dao.transaction.TransactionManager;
import com.bank.dto.Address;
import com.bank.dto.Client;
import com.bank.exception.TransactionException;
import com.bank.services.id_generator.IdGenerator;

import java.util.List;
import java.util.Objects;

public class ClientRegistrationService {
    private static ClientDAO clientDAO = DAOFactory.getClientDAO();

    public static void registerClientWithNewCard(Client client, Address address) {
        client.setAddressId(AddressRegistrationService.registerClientAddress(address));

        if (!clientAlreadyRegistered(client)) {
            client.setId(IdGenerator.getClientId());

            try {
                TransactionManager.beginTransaction();
                clientDAO.addClient(client);
            } catch (TransactionException e) {
                TransactionManager.rollBackTransaction();
                throw e;
            } finally {
                TransactionManager.commitTransaction();
            }

            CreditCardRegistrationService.registerNewCreditCardForClient(client);
        }
    }

    private static boolean clientAlreadyRegistered(Client client) {
        return alreadyRegisteredClient(client) != -1l;
    }

    private static Long alreadyRegisteredClient(Client client) {
        List<Client> clientList;

        try {
            TransactionManager.beginTransaction();
            clientList = clientDAO.getAllClients();
        } catch (TransactionException e) {
            TransactionManager.rollBackTransaction();
            throw e;
        } finally {
            TransactionManager.commitTransaction();
        }

        for (Client clientFromList : clientList) {
            if (Objects.equals(clientFromList.getLogin(), client.getLogin()) &&
                    Objects.equals(clientFromList.getName(), client.getName()) &&
                    Objects.equals(clientFromList.getSurname(), client.getSurname()) &&
                    Objects.equals(clientFromList.getBirthday(), client.getBirthday()) &&
                    (clientFromList.getAddressId() == client.getAddressId()) &&
                    Objects.equals(clientFromList.getEmail(), client.getEmail()) &&
                    Objects.equals(clientFromList.getPhone(), client.getPhone())) {
                return clientFromList.getId();
            }
        }
        return -1l;
    }
}
