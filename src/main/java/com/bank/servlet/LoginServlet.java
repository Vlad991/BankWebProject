package com.bank.servlet;

import com.bank.dto.Client;
import com.bank.services.ClientAuthenticationService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("message", req.getParameter("message"));
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/view/index.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        Client client = ClientAuthenticationService.getClientByLoginAndPassword(login, password);

        if (client == null) {
            req.setAttribute("message", "Login or password are incorrect!");
            req.getRequestDispatcher("login").forward(req, resp);
        }

        HttpSession session = req.getSession();
        session.setMaxInactiveInterval(1800);
        session.setAttribute("login", login);
        session.setAttribute("password", password);
        session.setAttribute("client", client);
        RequestDispatcher rd = req.getRequestDispatcher("client_menu");
        rd.forward(req, resp);
//
//            PrintWriter writer = resp.getWriter();
//            writer.println("<html><body<h2>Error</h2></body></html>");
//
//        RequestDispatcher rd1 = req.getRequestDispatcher("/WEB-INF/view/login.jsp");
//        rd.forward(req, resp);
    }
}
