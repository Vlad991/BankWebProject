package com.bank.services.registration;

import com.bank.dao.AddressDAO;
import com.bank.dao.DAOFactory;
import com.bank.dao.transaction.TransactionManager;
import com.bank.dto.Address;
import com.bank.exception.TransactionException;
import com.bank.services.id_generator.IdGenerator;

import java.util.List;
import java.util.Objects;

public class AddressRegistrationService {
    private static AddressDAO addressDAO = DAOFactory.getAddressDAO();

    public static Long registerClientAddress(Address address) {
        Long addressId;
        if (!addressAlreadyRegistered(address)) {
            addressId = IdGenerator.getAddressId();
            address.setId(addressId);

            try {
                TransactionManager.beginTransaction();
                addressDAO.addAddress(address);
            } catch (TransactionException e) {
                TransactionManager.rollBackTransaction();
                throw e;
            } finally {
                TransactionManager.commitTransaction();
            }

            return addressId;
        } else {
            return alreadyRegisteredAddress(address);
        }
    }

    private static boolean addressAlreadyRegistered(Address address) {
        return alreadyRegisteredAddress(address) != -1l;
    }

    private static Long alreadyRegisteredAddress(Address address) {
        List<Address> addressList;

        try {
            TransactionManager.beginTransaction();
            addressList = addressDAO.getAllAddresses();
        } catch (TransactionException e) {
            TransactionManager.rollBackTransaction();
            throw e;
        } finally {
            TransactionManager.commitTransaction();
        }

        for (Address addressFromList : addressList) {
            if (Objects.equals(addressFromList.getCountry(), address.getCountry()) &&
                    Objects.equals(addressFromList.getCity(), address.getCity()) &&
                    Objects.equals(addressFromList.getStreet(), address.getStreet()) &&
                    (addressFromList.getPostCode() == address.getPostCode())){
                return addressFromList.getId();
            }
        }
        return -1l;
    }

}
