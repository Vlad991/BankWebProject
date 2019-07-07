package com.bank.services;

import com.bank.dto.Address;
import com.bank.dto.Client;
import com.bank.dto.Date;
import com.bank.services.id_generator.IdGenerator;
import com.bank.services.registration.AddressRegistrationService;
import com.bank.services.registration.ClientRegistrationService;

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
//        System.out.println(AddressRegistrationService.registerClientAddress(
//                new Address(1l,
//                        "Ukraine",
//                        "Ivano-Frankivsk",
//                        "Guklive",
//                        3)));
        Client client1 = new Client(9L,
                "pilipka10",
                "Yuriy",
                "Pilipko",
                new Date(10, 05, 2000),
                2L,
                "yuriypilipko@gmail.com",
                "+380950952457",
                "123");
        Client client2 = new Client(10L,
                "pilipka10",
                "Yuriy",
                "Pilipko",
                new Date(10, 05, 2000),
                2L,
                "yurapilipko@gmail.com",
                "+380662873382",
                "123");
        Address address1 = new Address(1l,
                        "Ukraine",
                        "Ivano-Frankivsk",
                        "Guklive",
                        3);
        ClientRegistrationService.registerClientWithNewCard(client1, address1);
    }
}
