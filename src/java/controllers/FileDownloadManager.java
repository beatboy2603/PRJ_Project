/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.FileDao;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 *
 * @author Think
 */
@WebServlet(name = "FileDownloadManager", urlPatterns = {"/FileDownloadManager"})
public class FileDownloadManager extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String code = request.getParameter("f");
        Util.Base64 decrypt = new Util.Base64(code);
        String PermitIP = decrypt.getIp();
        int id = decrypt.getId();
        String ClientIP = request.getRemoteAddr();
        if(!ClientIP.equals(PermitIP)||decrypt.getTimestamp()>( new java.util.Date()).getTime()){
            response.sendRedirect("./File/"+id);
            return;
        }
        HttpSession session = request.getSession();
        String usernameInSession = (String)session.getAttribute("username");
        models.File get = null;
        try {
            dao.FileDao dao = new FileDao();
            get = dao.getFile(id);
        } catch (Exception ex) {
            Logger.getLogger(FileDownloadManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(get==null){
            return;
        }
        String path = getServletContext().getInitParameter("Storage")+get.getfOwner()+"\\"+id;
            
        File file = new File(path);
        
        String fileName = file.getName();

        if (!file.exists()) {
            System.out.println("Not found");
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        long length = file.length();
        String range = request.getHeader("Range");
        long start;
        long end;
        if (range != null) {
            if (!range.matches("^bytes=\\d*-\\d*$")) {
                response.setHeader("Content-Range", "bytes */" + length);
                response.sendError(HttpServletResponse.SC_REQUESTED_RANGE_NOT_SATISFIABLE);
                return;
            }
            start = Long.parseLong(range.substring(6, range.indexOf("-")));
            String part = range.substring(range.indexOf("-") + 1, range.length());
            end = part.isEmpty() ? length - 1 : Long.parseLong(part);
        } else {
            start = 0;
            end = length - 1;
        }

        if (start > end) {
            response.setHeader("Content-Range", "bytes */" + length);
            response.sendError(HttpServletResponse.SC_REQUESTED_RANGE_NOT_SATISFIABLE);
            return;
        }

        String contentType = getServletContext().getMimeType(fileName);
        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        
        response.reset();
        response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
        response.setHeader("Accept-Ranges", "bytes");

        FileInputStream input = null;
        OutputStream output = null;

        try {
            // Open streams.
            input = new FileInputStream(file);
            output = response.getOutputStream();
            response.setContentType(contentType);
            response.setHeader("Content-Range", "bytes " + start + "-" + end + "/" + length);
            response.setHeader("Content-Length", String.valueOf(length));
            response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
            byte[] buffer = new byte[1024];
            int read;
            input.skip(start);
            long toRead = length;
            while (toRead > 0) {
                read = input.read(buffer);
                toRead-=read;
                output.write(buffer);
            }
        } catch (IOException e) 
        {
            /*Do nothing since it is most likely user disconnected from download*/
        } finally {
            try {
                
                input.close();
                output.close();
            } catch (IOException e) {

            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
