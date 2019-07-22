package com.bank.servlet;

import com.bank.dao.transaction.TransactionManager;
import com.bank.dto.Address;
import com.bank.dto.Client;
import com.bank.dto.CreditCard;
import com.bank.services.ClientAuthenticationService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/client_menu")
public class ClientMenuServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String login = (String) session.getAttribute("login");
        String password = (String) session.getAttribute("password");
        Client client = (Client) session.getAttribute("client");
        if (login == null) {
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/view/index.jsp");
            rd.forward(req, resp);
        }

//        Client client = ClientAuthenticationService.getClientByLoginAndPassword(login, password);
        if (client == null) {
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/view/index.jsp");
            rd.forward(req, resp);
        }
        Address address = ClientAuthenticationService.getClientAddressByLoginAndPassword(login, password);
        List<CreditCard> creditCardList = ClientAuthenticationService.getClientCreditCardListByLoginAndPassword(login, password);

        session.setAttribute("address", address);
        session.setAttribute("creditCardList", creditCardList);
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/view/client_menu.jsp");
        rd.forward(req, resp);
//            String clientJson = new ObjectMapper().writeValueAsString(client);
//            String addressJson = new ObjectMapper().writeValueAsString(address);
//            resp.setContentType("application/json");
//            resp.getWriter().print(clientJson);
//            resp.getWriter().print(addressJson);
//            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/view/menu.html");
//            rd.forward(req, resp);
    }
}
