/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.FileDao;
import dao.PermitDao;
import dao.UserDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.File;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "Share", urlPatterns = {"/Share"})
public class ShareController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String fId = request.getParameter("fId");
            File file = (new FileDao()).getFile(Integer.parseInt(fId));
            List<String> usernames = (new UserDao()).getShareableUsers(Integer.parseInt(fId));
            request.setAttribute("usernames", usernames);
            request.setAttribute("file", file);
            
            RequestDispatcher view = request.getRequestDispatcher("share.jsp");
            view.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ShareController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String fId = request.getParameter("fId");
            String userToShare = request.getParameter("userToShare");
            (new PermitDao()).addPermit(Integer.parseInt(fId), userToShare);
            response.sendRedirect("./File");
        } catch (Exception ex) {
            Logger.getLogger(ShareController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
