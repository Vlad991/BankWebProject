package com.bank.servlet;

import com.bank.dto.Address;
import com.bank.dto.Client;
import com.bank.dto.Date;
import com.bank.services.registration.ClientRegistrationService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String day = request.getParameter("day");
        String month = request.getParameter("month");
        String year = request.getParameter("year");
        Date birthday = new Date(Integer.parseInt(day), Integer.parseInt(month), Integer.parseInt(year));
        String country = request.getParameter("country");
        String city = request.getParameter("city");
        String street = request.getParameter("street");
        int postcode = Integer.parseInt(request.getParameter("postcode"));
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String repeatPassword = request.getParameter("repeat_password");

        //Validation input data
        //... (not null, .. etc.)

        //Check if password repeats
//        if (!password.equals(repeatPassword)) {
//            request.setAttribute("not_matching_passwords", true);
//            RequestDispatcher notMatchingPasswords = request.getRequestDispatcher("/WEB-INF/view/registration.jsp");
//            notMatchingPasswords.forward(request, response);
//        }

//        request.setAttribute(" not_matching_passwords", false);

        Client newClient = new Client(login, name, surname, birthday, email, phone, password);
        Address newClientAddress = new Address(country, city, street, postcode);

        ClientRegistrationService.registerClientWithNewCard(newClient, newClientAddress);

        RequestDispatcher rd1 = request.getRequestDispatcher("/WEB-INF/view/client_menu.jsp");
        rd1.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("login") == null) {
            request.setAttribute("login", "");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/registration.jsp");
        rd.forward(request, response);
    }
}
