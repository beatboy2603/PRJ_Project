/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.FileDao;
import dao.PermitDao;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import static java.lang.Thread.sleep;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import models.File;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "File", urlPatterns = {"/File"})
@MultipartConfig
public class FileController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            HttpSession session = request.getSession();
            String usernameInSession = (String) session.getAttribute("username");
            FileDao dao = new FileDao();
            List<File> files;
            String fileType = request.getParameter("fileType") == null ? "myFiles" : request.getParameter("fileType").trim().equals("myFiles") ? "myFiles" : "sharedFiles";
            if (fileType.trim().equals("myFiles")) {
                files = dao.getAllFiles(usernameInSession);
            } else {
                files = dao.getAllSharedFiles(usernameInSession);
            }
            request.setAttribute("files", files);

            RequestDispatcher view = request.getRequestDispatcher("index.jsp");
            view.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(FileController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //add file
        try {
            Part filePart = request.getPart("file");
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            HttpSession session = (HttpSession) request.getSession();
            String username = (String) session.getAttribute("username");
            if ((new FileDao()).checkFile(username, fileName)) {
                request.setAttribute("error", "File already existed!");
                RequestDispatcher view = request.getRequestDispatcher("addFile.jsp");
                view.forward(request, response);
            } else {
                InputStream fileContent = filePart.getInputStream();
                byte[] buffer = new byte[fileContent.available()];
                fileContent.read(buffer);
                java.io.File targetFile = new java.io.File(getServletContext().getInitParameter("Storage") + username + "\\" + fileName);
                OutputStream outStream = new FileOutputStream(targetFile);
                outStream.write(buffer);
                String fName = fileName;
                String fSize = "" + filePart.getSize();
                String fOwner = username;
                String privacy = request.getParameter("privacy") == null ? "private" : "public";
                (new FileDao()).addFile(fName, fSize, fOwner, privacy);
                File addedFile = (new FileDao()).getFile(fOwner, fName);
                (new PermitDao()).addPermit(addedFile.getfId(), addedFile.getfOwner());
                fileContent.close();
                outStream.close();
                //sleep de kip copy file vao
                sleep(2000);
            }
            response.sendRedirect("./File");
        } catch (Exception ex) {
            Logger.getLogger(FileController.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("error", "No file was chosen!");
            RequestDispatcher view = request.getRequestDispatcher("addFile.jsp");
            view.forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
