/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.TomEEServlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig
@WebServlet(name = "Create", urlPatterns = {"/Create"})
public class Create extends HttpServlet {

    @EJB
    private DaoEjb daoEjb;     
 


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       boolean res_operation = false;
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        for (Part part : request.getParts()) {
            if ("image/jpeg".equals(part.getContentType())){
                 String nameF = part.getName();
                 res_operation = daoEjb.saveUser("Dmitr raf", "567777777", part.getInputStream());
            } 
        }
        
        try (PrintWriter out = response.getWriter()) {
                 out.println("результат операции: " + res_operation);
            }
    }

    

}
