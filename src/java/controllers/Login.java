/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.UserDao;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String usernameInSession = (String) session.getAttribute("username");
        if (usernameInSession == null) {
            RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/login.jsp");
            view.forward(request, response);
        } else {
            response.sendRedirect("./File");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            String usernameInSession = (String) session.getAttribute("username");
            if (usernameInSession == null) {
                String username = request.getParameter("username");
                String pass = request.getParameter("pass");
                if (username.isEmpty() || pass.isEmpty()) {
                    request.setAttribute("error", "empty field error");
                    RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/login.jsp");
                    view.forward(request, response);
                }
                if(Util.Administrator.loginAdmin(username, pass, getServletContext()))
                {
                    session.setAttribute("username", username);
                    session.setAttribute("admin", username);
                    response.sendRedirect("./User");
                }else if ((new UserDao()).checkUser(username, pass)) {
                    session.setAttribute("username", username);
                    response.sendRedirect("./File");
                } else {
                    request.setAttribute("error", "login failed");
                    RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/login.jsp");
                    view.forward(request, response);
                }
            } else {
                response.sendRedirect("./File");
            }
        } catch (Exception ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
