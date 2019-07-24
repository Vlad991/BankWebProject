package com.bank.servlet;

import com.bank.dto.Administrator;
import com.bank.dto.Client;
import com.bank.services.ClientListService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin_menu")
public class AdminMenuServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Administrator admin = (Administrator) session.getAttribute("admin");
        //todo (check if login and password are correct

        List<Client> clientList = ClientListService.getAllClinets();

        req.setAttribute("clientList", clientList);
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/view/admin_menu.jsp");
        rd.forward(req, resp);
    }
}
