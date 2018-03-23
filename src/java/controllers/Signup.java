/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.UserDao;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "Signup", urlPatterns = {"/Signup"})
public class Signup extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/signup.jsp");
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String pass = request.getParameter("pass");
        try {
            if (username.isEmpty() || pass.isEmpty()) {
                request.setAttribute("error", "empty field error");
                RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/signup.jsp");
                view.forward(request, response);
            }
            if ((new UserDao()).checkUser(username)) {
                request.setAttribute("error", "username already existed!");
                RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/signup.jsp");
                view.forward(request, response);
            } else {
                (new UserDao()).addUser(username, pass);
                Files.createDirectories(Paths.get(getServletContext().getInitParameter("Storage") + username));
                response.sendRedirect("./Login");
            }
        } catch (Exception ex) {
            Logger.getLogger(Signup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
