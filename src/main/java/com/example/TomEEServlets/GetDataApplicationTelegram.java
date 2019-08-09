/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.TomEEServlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.annotation.Resource;
import javax.enterprise.inject.spi.BeanManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Артур
 */
@WebServlet(name = "GetDataApplicationTelegram", urlPatterns = {"/getDataApplicationTelegram.xhtml"})
public class GetDataApplicationTelegram extends HttpServlet {

   @Resource
   BeanManager beanManager;
 
   @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         StartApplicationTelegram cdiTelegram = BinTelegram.getBeanInstance( beanManager, StartApplicationTelegram.class );
         
         response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletTelegram</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletTelegram at " + request.getContextPath() + "</h1>");
            out.println("<h1>Servlet ServletTelegram at " + cdiTelegram.getKodTelegram() + "</h1>");
            out.println("<h1>Servlet ServletTelegram at " + cdiTelegram.hashCode() + "</h1>");
            out.println("<h1>Servlet ServletTelegram at " + cdiTelegram.isAccessed() + "</h1>");
            out.println("<h1>Servlet ServletTelegram at " + cdiTelegram.getApplication().toString() + "</h1>");
             
            out.println("</body>");
            out.println("</html>");
        }

    }

 
}
