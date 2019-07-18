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
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String existingLogin = (String)request.getParameter("existing_login");
//        String existingPassword = (String) request.getParameter("existing_password");
//        String newLogin = (String)request.getParameter("new_login");
//
//        TransactionManager.beginTransaction();
//        ClientDAO clientDAO = new ClientDAO();
//        Client client = clientDAO.getClientByLogin(existingLogin);
//        TransactionManager.commitTransaction();
//
//        request.setAttribute("new_login", newLogin);
//
//        if (client.getPassword().equals(existingPassword)) {
//            request.setAttribute("login", existingLogin);
//            request.setAttribute("password", existingPassword);
//            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/client_menu.jsp");
//            rd.forward(request, response);
//        } else {
//            PrintWriter writer = response.getWriter();
//            writer.println("<html><body<h2>Error</h2></body></html>");
//        }
//    }
}
