
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


@WebServlet(name = "ServletRedirectTelegram", urlPatterns = {"/ServletRedirectTelegram"})
public class ServletRedirectTelegram extends HttpServlet {

    @Resource
    BeanManager beanManager;
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        StartApplicationTelegram cdiTelegram =  BinTelegram.getBeanInstance(beanManager, StartApplicationTelegram.class);
        boolean accessed = cdiTelegram.isAccessed();
        if (accessed==true){
           request.getRequestDispatcher("getDataApplicationTelegram.xhtml").forward(request,response);
        }else{
           request.getRequestDispatcher("startApplicationTelegram.xhtml").forward(request,response); 
        }    
    }

}
