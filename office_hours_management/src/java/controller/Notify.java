
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
import model.Notification;


@WebServlet(name = "Notify", urlPatterns = {"/Notify"})
public class Notify extends HttpServlet {

    DaoNotifications daoNotifications = new DaoNotifications();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
        
            Notification notification = new Notification();
             HttpSession session = request.getSession();
           // we will get the email by session or get attribute ...
           // String email = session.getAttribute("email").toString();
           // System.out.print("###################" + email);
            
            notification.setFromEmail("naserfakhry100@gmail.com");
            notification.setToEmail("mariamnasser979@gmail.com");
            notification.setNotificationMessage("Message Notification we will git it from session but this is just for test");
            
            if(daoNotifications.sendNotificationToSystem(notification))
            {
                System.out.print("################### Notifications done sucessfully");
       
            }
            
            
        }catch(Exception e){
            System.out.print("################### Errrorrrrrrr"); 
        
        
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
