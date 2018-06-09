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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.File;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "Delete", urlPatterns = {"/Delete"})
public class DeleteController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String fId = request.getParameter("fId");
            File file = (new FileDao()).getFile(Integer.parseInt(fId));
            int fSize = file.getfSize();
            String fOwner = file.getfOwner();
            int newQuota = (new UserDao()).getUser(fOwner).getQuota() - fSize;
            (new PermitDao()).deleteAllPermit(Integer.parseInt(fId));
            (new FileDao()).deleteFile(Integer.parseInt(fId));
            (new UserDao()).updateQuota(fOwner, newQuota);
            Path path = Paths.get(getServletContext().getInitParameter("Storage") + file.getfOwner() + "\\" + file.getfName());
            Files.delete(path);
            HttpSession session = request.getSession();
            String admin = (String) session.getAttribute("admin");
            if (admin == null) {
                response.sendRedirect("./File");
            }else{
                String username = (String) session.getAttribute("username");
                response.sendRedirect("./File?username="+fOwner);
            }
            
        } catch (Exception ex) {
            Logger.getLogger(DeleteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
