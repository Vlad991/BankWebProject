package com.bank.servlets;

import com.bank.dao.AddressDAO;
import com.bank.dao.ClientDAO;
import com.bank.dao.CreditCardDAO;
import com.bank.dao.DAOFactory;
import com.bank.dao.transaction.TransactionManager;
import com.bank.dto.Address;
import com.bank.dto.Client;
import com.bank.dto.CreditCard;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/client_menu")
public class ClientMenuServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        TransactionManager.beginTransaction();
        ClientDAO clientDAO = DAOFactory.getClientDAO();
        AddressDAO addressDAO = DAOFactory.getAddressDAO();
        CreditCardDAO creditCardDAO = DAOFactory.getCreditCardDAO();

        Client client = clientDAO.getClientByLogin(login);
        Address address = addressDAO.getAddressById(client.getAddressId());
        List<CreditCard> creditCardList = creditCardDAO.getCreditCardsByClientId(client.getId());
        TransactionManager.commitTransaction();

        if (client.getPassword().equals(password)) {
            req.setAttribute("login", login);
            req.setAttribute("password", password);
            req.setAttribute("name", client.getName());
            req.setAttribute("surname", client.getSurname());
            req.setAttribute("birthday", client.getBirthday());
            req.setAttribute("country", address.getCountry());
            req.setAttribute("city", address.getCity());
            req.setAttribute("street", address.getStreet());
            req.setAttribute("postcode", address.getPostCode());
            req.setAttribute("email", client.getEmail());
            req.setAttribute("phone", client.getPhone());
            req.setAttribute("creditCards", creditCardList);
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/view/client_menu.jsp");
            rd.forward(req, resp);
        } else {
            PrintWriter writer = resp.getWriter();
            writer.println("<html><body<h2>Error</h2></body></html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
