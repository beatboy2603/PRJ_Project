/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.FileDao;
import dao.PermitDao;
import java.io.IOException;
import java.io.PrintWriter;
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
            (new PermitDao()).deleteAllPermit(Integer.parseInt(fId));
            (new FileDao()).deleteFile(Integer.parseInt(fId));
            Path path = Paths.get(getServletContext().getInitParameter("Storage") + file.getfOwner() + "\\" + file.getfName());
            Files.delete(path);
            response.sendRedirect("./File");
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
