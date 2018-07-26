/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Util.Base64;
import dao.FileDao;
import dao.PermitDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.File;
import models.Encode;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "Download", urlPatterns = {"/Download"})
public class DownloadController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String fId = request.getParameter("fId");
            File file = (new FileDao()).getFile(Integer.parseInt(fId));
            if (file.getPrivacy().equalsIgnoreCase("private")) {
                HttpSession session = request.getSession();
                String username = (String) session.getAttribute("username");
                if(session.getAttribute("admin")!=null){}
                else if(!(new PermitDao()).checkPermit(Integer.parseInt(fId), username)){
                    response.sendRedirect("./File");
                    return;
                }
            }
            String IP = request.getRemoteAddr();

            long timestamp = java.util.Calendar.getInstance().getTimeInMillis();
            Base64 encrypt = new Base64(IP, Integer.parseInt(fId), timestamp);
            String link = encrypt.getEncoded();
            request.setAttribute("link", link);
            request.setAttribute("file", file);
            RequestDispatcher view = request.getRequestDispatcher("download.jsp");
            view.forward(request, response);

        } catch (Exception ex) {
            Logger.getLogger(DownloadController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
