/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.TomEEServlets;

import com.github.badoualy.telegram.api.Kotlogram;
import com.github.badoualy.telegram.api.TelegramApp;
import com.github.badoualy.telegram.api.TelegramClient;
import com.github.badoualy.telegram.tl.api.TLContact;
import com.github.badoualy.telegram.tl.api.TLInputUser;
import com.github.badoualy.telegram.tl.api.auth.TLAuthorization;
import com.github.badoualy.telegram.tl.api.TLUser;
import com.github.badoualy.telegram.tl.api.auth.TLSentCode;
import com.github.badoualy.telegram.tl.api.contacts.TLAbsContacts;
import com.github.badoualy.telegram.tl.api.contacts.TLContacts;
import com.github.badoualy.telegram.tl.exception.RpcErrorException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Артур
 */
@WebServlet(name = "Kotog", urlPatterns = {"/Kotog"})
public class Kotog extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Kotog</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Kotog at " + request.getContextPath() + "</h1>");
            String PHONE_NUMBER = "+89213946660";
            TelegramApp application = new TelegramApp(868145, "f060da0313295164b197cea7de9f5e53", "Model",  "SysVer", "1", "en");
            out.println("<h1>connect app </h1>");
            ApiStorage state = new ApiStorage();
            out.println("<h1>connect state </h1>");
            TelegramClient client = Kotlogram.getDefaultClient(application, state);
            out.println("<h1>connect TelegramClient </h1>");
            
            try {
                 // Send code to account
                 TLSentCode sentCode = client.authSendCode(false, PHONE_NUMBER, true);
                 System.out.println("Authentication code: ");
                 String code = new Scanner(System.in).nextLine();
                 //идет получение данных по коду
                 TLAuthorization authorization = client.authSignIn(PHONE_NUMBER, sentCode.getPhoneCodeHash(), code);
                 TLUser self = authorization.getUser().getAsUser();
                // TLContacts tLAbsContacts = (TLContacts) client.contactsGetContacts("");
                 //tLAbsContacts
                
                 System.out.println("You are now signed in as " + self.getFirstName() + " " + self.getLastName() + " @" + self.getUsername());
                 } catch (RpcErrorException | IOException e) {
                    e.printStackTrace();
                 } finally {
                      client.close(); // Important, do not forget this, or your process won't finish
                 }
            out.println("</body>");
            out.println("</html>");
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
