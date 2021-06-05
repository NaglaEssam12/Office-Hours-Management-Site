/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DaoNotifications;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author User
 */
@WebServlet(name = "showMyNotifications", urlPatterns = {"/showMyNotifications"})
public class showMyNotifications extends HttpServlet {

   DaoNotifications daoNotifications = new DaoNotifications();
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           
           // we will get the email by session or get attribute ...
            HttpSession session = request.getSession();
            String email = session.getAttribute("email").toString();
            System.out.print("###################" + email);
         //  String email= "mariamnasser979@gmail.com";
           String output = daoNotifications.showMyNotifications(email);
           if(output != null)
            {
                System.out.print("################### show Notifications done sucessfully 11");
                System.out.print(output);
                session.setAttribute("output",output);
                response.sendRedirect("show_my_notifications.jsp");
       
            }
            
            
        }catch(Exception e){
            System.out.print("################### Errrorrrrrrr"); 
        
        
        }
        }
    



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
