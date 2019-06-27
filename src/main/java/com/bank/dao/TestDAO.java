package com.bank.dao;

import com.bank.dao.transaction.TransactionManager;
import com.bank.dto.Client;

import java.sql.Date;

public class TestDAO {

	public static void main(String[] args) throws Exception {
		TransactionManager.beginTransaction();
		ClientDAO clientDAO = new ClientDAO();
		clientDAO.addClient(new Client(1L,
				"vlad99",
				"Vladyslav",
				"Kuzma",
				new Date(1999, 10, 14),
				1L,
				"vladkuzma99@gamil.com",
				"0662873382",
				"123root@"));
		TransactionManager.commitTransaction();
	}

}
