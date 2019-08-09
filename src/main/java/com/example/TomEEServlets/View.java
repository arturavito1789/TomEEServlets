/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.TomEEServlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Артур
 */
@WebServlet(name = "View", urlPatterns = {"/View"})
public class View extends HttpServlet {

    @EJB
    private DaoEjb daoEjb;  
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
         response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
             out.println("1");  
             List<Users> users = daoEjb.getAllUsers(out);
             out.println("3");  
             for (Users user : users) {
                 String name = user.getName();
                 String phone = user.getPhone();
                 byte[] b = user.getFoto();
                 byte[] encodedBytes = Base64.encodeBase64(b);
                 System.out.println("encodedBytes " + new String(encodedBytes));
                 String src = "data:image/jpeg;base64," + new String(encodedBytes);
                 out.println(" <img src=" +src + " class=\"img-fluid img-circle\">");
                 out.println("<div class=\"col-5 width-height-img align-self-center text-left text-white font-italic\"> " + name + " " + phone + "</div>");
                 out.println("</div>"); 
                 out.println("<div class=\"row separator_row\">");
                 out.println("<div class=\"col-12\"><hr/></div>");
                 out.println("</div>");  
        
            }
        }
       
    }


}
