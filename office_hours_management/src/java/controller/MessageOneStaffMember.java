/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DaoFinder;
import dao.DaoMessage;
import dao.DaoViewStaffMemberMessages;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Message;

/**
 *
 * @author DELL
 */
@WebServlet(name = "MessageOneStaffMember", urlPatterns = {"/MessageOneStaffMember"})
public class MessageOneStaffMember extends HttpServlet {

    NotifyUser notifyUser = new NotifyUser();
    DaoMessage daoMessage = new DaoMessage();
    DaoFinder daoFinder = new DaoFinder();
    DaoViewStaffMemberMessages daoViewStaffMemberMessages = new DaoViewStaffMemberMessages();
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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        Integer fromUserID = Integer.parseInt(request.getSession().getAttribute("userID").toString());
        Integer toUserID = Integer.parseInt(request.getParameter("toID"));
        String fromUserEmail = request.getSession().getAttribute("email").toString();
        String toUserEmail = daoFinder.findContactForUser(toUserID);
        
        HttpSession session = request.getSession();
        session.setAttribute("toUserEmail",toUserEmail);
        
        String messageSubject = request.getParameter("messageSubject").toString();
        String actualMessage = request.getParameter("messageContent").toString();
        LocalDate date = LocalDate.now();
        
        Message message = new Message();
        message.setFromUserID(fromUserID);
        message.setToUserID(toUserID);
        message.setActualMassage(actualMessage);
        message.setDate(date);
        try{
            notifyUser.sendEmail("officeorganization15@gmail.com", toUserEmail, messageSubject, actualMessage);
            notifyUser.notify(fromUserEmail, toUserEmail, actualMessage);
            boolean status = daoMessage.insertMessage(message);
            if (status == true){
                out.print("<h2 style=\"color: #3366cc; text-align: center\">operation done successfully</h2>");
            }
            else{
                out.print("<h2 style=\"color: #3366cc; text-align: center\">operation failed!</h2>");
            }
            daoFinder.closeConnection();
        }catch (Exception e) {
           System.out.print("@@@@#### Error in MessageOneStaffMember @@@@####  " + e);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(MessageOneStaffMember.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(MessageOneStaffMember.class.getName()).log(Level.SEVERE, null, ex);
        }
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
