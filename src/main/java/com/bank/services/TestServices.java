package com.bank.services;

import com.bank.dto.Address;
import com.bank.services.id_generator.IdGenerator;
import com.bank.services.registration.AddressRegistrationService;

public class TestServices {
    public static void main(String[] args) {
//        long clientId = IdGenerator.getClientId();
//        long addressId = IdGenerator.getAddressId();
//        int adminId = IdGenerator.getAdministratorId();
//        long cardId = IdGenerator.getCreditCardId();
//        long userId = IdGenerator.getUserId();
//        System.out.println(clientId);
//        System.out.println(addressId);
//        System.out.println(adminId);
//        System.out.println(cardId);
//        System.out.println(userId);
        System.out.println(AddressRegistrationService.registerClientAddress(
                new Address(1l,
                        "Ukraine",
                        "Ivano-Frankivsk",
                        "Guklive",
                        3)));

    }
}