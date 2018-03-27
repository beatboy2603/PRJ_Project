/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import javax.servlet.ServletContext;

/**
 *
 * @author Think
 */
public class Administrator {
    public static boolean loginAdmin(String username,String pass,ServletContext context){
        
        String adminname = context.getInitParameter("admin");
        String adminpass = context.getInitParameter("pass");
        return adminname.equals(username) && adminpass.equals(pass);
    
    }
    public static boolean checkAdmin(String username,ServletContext context){
        String adminname = context.getInitParameter("admin");
        return username.equals(adminname);
    }
}
