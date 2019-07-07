package com.bank.dao;

import com.bank.dao.transaction.TransactionManager;
import com.bank.dto.Client;
import com.bank.dto.Date;

public class TestDAO {

	public static void main(String[] args) throws Exception {
		Client client1 = new Client(9L,
				"pilipka10",
				"Yuriy",
				"Pilipko",
				new Date(10, 05, 2000),
				2L,
				"yurapilipko@gamil.com",
				"+380662873382",
				"123");
		Client client2 = new Client(10L,
				"pilipka10",
				"Yuriy",
				"Pilipko",
				new Date(10, 05, 2000),
				2L,
				"yurapilipko@gamil.com",
				"+380662873382",
				"123");
//		TransactionManager.beginTransaction();
//		ClientDAO clientDAO = DAOFactory.getClientDAO();
//		clientDAO.addClient(client1);
//		TransactionManager.commitTransaction();
//
//		TransactionManager.beginTransaction();
//		clientDAO.removeClient(client1.getId());
//		TransactionManager.commitTransaction();
	}
}
