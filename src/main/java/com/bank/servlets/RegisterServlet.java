package com.bank.servlets;

import com.bank.dao.AddressDAO;
import com.bank.dao.ClientDAO;
import com.bank.dao.DAOFactory;
import com.bank.dao.transaction.TransactionManager;
import com.bank.dto.Address;
import com.bank.dto.Client;
import com.bank.dto.Date;
import com.bank.exception.TransactionException;

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
            String day = (String) request.getParameter("day");
            String month = (String) request.getParameter("month");
            String year = (String) request.getParameter("year");
            Date birthday = new Date(Integer.parseInt(day), Integer.parseInt(month), Integer.parseInt(year));
            String country = (String) request.getParameter("country");
            String city = (String) request.getParameter("city");
            String street = (String) request.getParameter("street");
            int postcode = Integer.parseInt((String) request.getParameter("postcode"));

            Long clientAddressId = new Long(1);
            try {
                TransactionManager.beginTransaction();
                AddressDAO addressDAO = DAOFactory.getAddressDAO();
                List<Long> address_ids = addressDAO.getAddressIds();
                for (Long i = 1L; i < address_ids.size() + 2L; i++) {
                    if (address_ids.indexOf(new Long(i)) == -1) {
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
            } catch (TransactionException e) {
                TransactionManager.rollBackTransaction();
                throw e;
            } finally {
                TransactionManager.commitTransaction();
            }

            String email = (String) request.getParameter("email");
            String phone = (String) request.getParameter("phone");
            String password = (String) request.getParameter("password");

            Address address = new Address(clientAddressId, country, city, street, postcode);
            Long clientId = 1l;
            ClientDAO clientDAO;

            try {
                TransactionManager.beginTransaction();
                AddressDAO addressDAO1 = DAOFactory.getAddressDAO();
                if (!addressDAO1.addressIsExist(address)) {
                    addressDAO1.addAddress(address);
                }
                clientDAO = DAOFactory.getClientDAO();
                List<Long> clientIds = clientDAO.getClientIds();
                for (int i = 1; i <= clientIds.size() + 2; i++) {
                    if (clientIds.indexOf(new Long(i)) == -1) {
                        clientId = new Long(i);
                        break;
                    }
                }
            } catch (TransactionException e) {
                TransactionManager.rollBackTransaction();
                throw e;
            } finally {
                TransactionManager.commitTransaction();
            }


            Client client = new Client(clientId, login, name, surname, birthday, clientAddressId, email, phone, password);

            try {
                TransactionManager.beginTransaction();
                clientDAO = DAOFactory.getClientDAO();
                clientDAO.addClient(client);
            } catch (TransactionException e) {
                TransactionManager.rollBackTransaction();
                throw e;
            } finally {
                TransactionManager.commitTransaction();
            }

            RequestDispatcher rd1 = request.getRequestDispatcher("/view/client_menu.jsp");
            rd1.forward(request, response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("/view/registration.jsp");
            rd.forward(request, response);
        }
    }
}
