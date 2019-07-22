package com.bank.servlet;

import com.bank.dto.Client;
import com.bank.dto.CreditCard;
import com.bank.services.CreditCardAuthentificationService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/client_menu/card_menu")
public class CardMenuServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Client client = (Client) session.getAttribute("client");
        String id = req.getParameter("card_id");
        Long cardId = Long.valueOf(req.getParameter("card_id"));
        int pin = Integer.parseInt(req.getParameter("pin"));

        CreditCard creditCard = CreditCardAuthentificationService.getCreditCardByIdAndPin(cardId, pin);

        if (creditCard == null) {
            req.setAttribute("message", "Card doesn't exists!");
            RequestDispatcher rd = req.getRequestDispatcher("login");
            rd.forward(req, resp);
        }

        if (creditCard.getClientId() != client.getId()) {
            req.setAttribute("message", "Card doesn't belong to your Client!");
            RequestDispatcher rd = req.getRequestDispatcher("login");
            rd.forward(req, resp);
        }

        if (creditCard.getPin() != pin) {
            req.setAttribute("message", "Pin code is invalid!");
            RequestDispatcher rd = req.getRequestDispatcher("login");
            rd.forward(req, resp);
        }

        req.setAttribute("id", cardId);
        req.setAttribute("code", creditCard.getCode());
        req.setAttribute("date", creditCard.getDate());
        req.setAttribute("status", creditCard.getStatus());
        req.setAttribute("sum", creditCard.getSum());
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/view/card_menu.jsp");
        rd.forward(req, resp);
    }
}
