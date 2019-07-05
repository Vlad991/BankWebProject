package com.bank.servlets;

import com.bank.dao.ClientDAO;
import com.bank.dao.transaction.TransactionManager;
import com.bank.dto.Client;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = (String)request.getParameter("existing_login");
        String password = (String) request.getParameter("existing_password");

        TransactionManager.beginTransaction();
        ClientDAO clientDAO = new ClientDAO();
        Client client = clientDAO.getClientByLogin(login);
        TransactionManager.commitTransaction();

        if (client.getPassword().equals(password)) {
            request.setAttribute("login", login);
            request.setAttribute("password", password);
            RequestDispatcher rd = request.getRequestDispatcher("/view/client_menu.jsp");
            rd.forward(request, response);
        } else {
            PrintWriter writer = response.getWriter();
            writer.println("<html><body<h2>Error</h2></body></html>");
        }
    }
}
