package ua.lviv.lgs.Servlets;

import org.apache.log4j.Logger;
import ua.lviv.lgs.dao.service.UserService;
import ua.lviv.lgs.dao.service.impl.UserServiceImpl;
import ua.lviv.lgs.domain.Role;
import ua.lviv.lgs.domain.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "RegistrationServlet", value = "/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService userService = UserServiceImpl.getUserService();
    private static Logger LOGGER = Logger.getLogger(RegistrationServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");

        if (!email.isEmpty() && !firstName.isEmpty() && !lastName.isEmpty() && !password.isEmpty()) {
            try {
                userService.create(new User(email, firstName, lastName, password, Role.USER.toString()));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("Seccess");

        }
    }
}
