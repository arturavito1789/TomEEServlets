/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.TomEEServlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.io.IOUtils;

@Stateless
public class DaoEjb {
    
    private String name = "EGB init";

    @PersistenceContext(unitName = "Phonebook")
    private EntityManager entityManager;
    
      
    public String getName() {
        return name;
    }
    
    
    public List<Users> getAllUsers(PrintWriter out){
        List<Users> us = null;
        out.println("2");  
         try{
             out.println(entityManager.toString());
        }catch (Exception e){
             out.println(e.getMessage());  
        }
        
        try{
             us = entityManager.createQuery("from Users u", Users.class).getResultList();
        }catch (Exception e){
             out.println(e.getMessage());  
        }
        
        return us;
    }
    
     public List<Users> getAllUsersD(){
        return entityManager.createQuery("from Users u", Users.class).getResultList();
    }
    
    public boolean deleteAllUser(){
        List<Users> users = getAllUsersD();
        for (Users user:users) {
             entityManager.remove(user);
        }
        return true;
     }
    
    public boolean saveUser(String nameParam, String phoneParam, InputStream foto){
      
        Users user = new Users();
        user.setName(nameParam);
        user.setPhone(phoneParam);
        try {
            byte[] bytes = IOUtils.toByteArray(foto);
            user.setFoto(bytes);
            foto.close();
        } catch (IOException e) {
            System.out.println(e.getCause());
        }
        
        try{
            entityManager.persist(user);
           // entityManager.getTransaction().commit();
        }
        catch (Exception ex) {
           // entityManager.getTransaction().rollback();
            return false;
        } 
        return true;
    }
}
