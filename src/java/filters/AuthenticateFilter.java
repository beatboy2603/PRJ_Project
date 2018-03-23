/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
@WebFilter(urlPatterns = { "/*" })
public class AuthenticateFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        return;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        
        String requestPath = req.getRequestURI();
        if(requestPath.endsWith("Login"))
        {
            HttpSession session = req.getSession();
            if(session.getAttribute("username")!=null)
            {
                ((HttpServletResponse)response).sendRedirect("./File");
            }
            else
            {
                chain.doFilter(request, response);
            }
        }else if(requestPath.endsWith("Logout"))
        {
            chain.doFilter(request, response);
        }else if(requestPath.endsWith("Signup"))
        {
            HttpSession session = req.getSession();
            if(session.getAttribute("username")!=null)
            {
                ((HttpServletResponse)response).sendRedirect("./File");
            }
            else
            {
                chain.doFilter(request, response);
            }
        }else if(requestPath.endsWith("Download")){
            if(((HttpServletRequest)request).getParameter("fId") != null){
                chain.doFilter(request, response);
            }else{
                ((HttpServletResponse)response).sendRedirect("./Login");
            }
        }else
        {
            HttpSession session = req.getSession();
            if(session.getAttribute("username")!=null)
            {
                chain.doFilter(request, response);
            }
            else
            {
                ((HttpServletResponse)response).sendRedirect("./Login");
            }
        }
    }

    @Override
    public void destroy() {
        return;
    }
    
}