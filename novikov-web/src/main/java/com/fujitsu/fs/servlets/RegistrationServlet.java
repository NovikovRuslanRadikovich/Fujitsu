package com.fujitsu.fs.servlets;


import com.fujitsu.fs.dao.UserDao;
import com.fujitsu.fs.dao.UserDaoImpl;
import com.fujitsu.fs.services.UserService;
import com.fujitsu.fs.services.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    UserService userService;
    UserDao userDao;

    public void init() {
        userDao = UserDaoImpl.getInstance();
        userService = new UserServiceImpl(userDao);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
        response.sendRedirect("/registration.ftl");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if(userService.isRegistered(username,password)) {
            request.setAttribute("exists",true);
            getServletConfig().getServletContext().getRequestDispatcher("/registration.ftl").forward(request,response);
        } else {
            userService.save(username,password);
            request.getSession().setAttribute("username",username);
            response.sendRedirect("/home");
        }
    }

}
