package com.bank.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class WelcomeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String send = (String) request.getAttribute("send");
//        if (send.equals("login")) {
            response.getWriter().println("<h1>ok</h1>");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login");
            requestDispatcher.forward(request, response);
//        } else if (send.equals("register")) {
//            RequestDispatcher requestDispatcher1 = request.getRequestDispatcher("/registr");
//            requestDispatcher1.forward(request, response);
//        }


    }
}
