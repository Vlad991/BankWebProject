package com.bank.servlet;

import com.bank.dto.CreditCard;
import com.bank.services.CreditCardAuthentificationService;
import com.bank.services.MoneyTransferService;
import com.bank.services.TransactionResult;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/client_menu/card_menu/transfer_money")
public class MoneyTransferServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long senderId = Long.valueOf(req.getParameter("id"));
        int senderPin = Integer.parseInt(req.getParameter("pin"));
        Long recipientId = Long.valueOf(req.getParameter("recipient_id"));
        Long sum = Long.valueOf(req.getParameter("sum"));

        CreditCard senderCard = CreditCardAuthentificationService
                .getCreditCardByIdAndPin(senderId, senderPin);
        if (senderCard == null) {
            req.setAttribute("message", "Pin code is invalid!");
            RequestDispatcher rd = req.getRequestDispatcher("login");
            rd.forward(req, resp);
        }

        TransactionResult transactionResult = MoneyTransferService
                .transferMoneyFromAccountToAnotherAccount(senderId, recipientId, sum);
        if (transactionResult == TransactionResult.FAILED) {
            req.setAttribute("message", "Transaction Failed!");
            RequestDispatcher rd = req.getRequestDispatcher("login");
            rd.forward(req, resp);
        }
        RequestDispatcher rd = req.getRequestDispatcher("/client_menu/card_menu");
        rd.forward(req, resp);
    }
}
