package com.bank.dao;

import com.bank.model.administrator.Administrator;
import com.bank.model.client.Client;

public class TestDAO {

	public static void main(String[] args) throws Exception {
		ClientDAO clientDAO = new ClientDAO();
        Client vasya = clientDAO.getClientByName("Vasya");
        System.out.println(vasya);
	}

}
