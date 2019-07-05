package com.bank.servlets;

import com.bank.dao.AddressDAO;
import com.bank.dao.ClientDAO;
import com.bank.dao.transaction.TransactionManager;
import com.bank.dto.Address;
import com.bank.dto.Client;
import com.bank.dto.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = (String) request.getParameter("login");
        String name = (String) request.getParameter("name");

        if (name != null) {
            String surname = (String) request.getParameter("surname");
            String date = (String) request.getParameter("date");
            String month = (String) request.getParameter("month");
            String year = (String) request.getParameter("year");
            Date birthday = new Date(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(date));
            String country = (String) request.getParameter("country");
            String city = (String) request.getParameter("city");
            String street = (String) request.getParameter("street");
            int postcode = Integer.parseInt((String) request.getParameter("postcode"));

            TransactionManager.beginTransaction();
            Long clientAddressId = new Long(1);
            AddressDAO addressDAO = new AddressDAO();
            List<Long> address_ids = addressDAO.getAddressIds();
            for (Long i = new Long(1); i < address_ids.size(); i++) {
                if (address_ids.indexOf(i) == -1) {
                    clientAddressId = i;
                    break;
                }
            }
            for (Long addressId : address_ids) {
                Address address = addressDAO.getAddressById(addressId);
                if (address.getCountry().equals(country)
                        && address.getCity().equals(city)
                        && address.getStreet().equals(street)
                        && address.getPostCode() == postcode) {
                    clientAddressId = address.getId();
                    break;
                }
            }
//            TransactionManager.commitTransaction();

            String email = (String) request.getParameter("email");
            String phone = (String) request.getParameter("phone");
            String password = (String) request.getParameter("password");

            Address address = new Address(clientAddressId, country, city, street, postcode);

//            TransactionManager.beginTransaction();
            AddressDAO addressDAO1 = new AddressDAO();
            if (!addressDAO1.addressIsExist(address)) {
                addressDAO1.addAddress(address);
            }
//            TransactionManager.commitTransaction();

            ClientDAO clientDAO = new ClientDAO();
            Long clientId = new Long(1);
            List<Long> clientIds = clientDAO.getClientIds();
            for (int i = 1; i <= clientIds.size(); i++) {
                if (clientIds.indexOf(new Long(i)) == -1) {
                    clientId = new Long(i);
                    break;
                }
            }

            Client client = new Client(clientId, login, name, surname, birthday, clientAddressId, email, phone, password);

//            TransactionManager.beginTransaction();
            clientDAO = new ClientDAO();
            clientDAO.addClient(client);
            TransactionManager.commitTransaction();

            RequestDispatcher rd1 = request.getRequestDispatcher("/view/client_menu.jsp");
            rd1.forward(request, response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("/view/registration.jsp");
            rd.forward(request, response);
        }
    }
}
