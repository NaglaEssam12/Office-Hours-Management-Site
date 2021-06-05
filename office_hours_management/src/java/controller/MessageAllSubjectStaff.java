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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Message;
import model.User;

/**
 *
 * @author DELL
 */
@WebServlet(name = "MessageAllSubjectStaff", urlPatterns = {"/MessageAllSubjectStaff"})
public class MessageAllSubjectStaff extends HttpServlet {
    NotifyUser notifyUser = new NotifyUser();
    DaoFinder daoFinder = new DaoFinder();
    DaoMessage daoMessage = new DaoMessage();
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
        PrintWriter out = response.getWriter();
        try{
            ArrayList<User> staffMembers = new ArrayList<>();
            
            Integer subjectID = Integer.parseInt(request.getParameter("toID").toString());
            String messageSubject = request.getParameter("messageSubject").toString();
            String actualMessage = request.getParameter("messageContent").toString();
            
            Integer fromUserID = Integer.parseInt(request.getSession().getAttribute("userID").toString());
            String fromUserEmail = request.getSession().getAttribute("email").toString();
            
            LocalDate date = LocalDate.now();
            
            staffMembers = daoFinder.findStaffBySubject(subjectID);
            for (int i = 0; i < staffMembers.size(); i++) {
                Integer toUserID = staffMembers.get(i).getUserID();
                String toUserEmail = daoFinder.findContactForUser(toUserID);
                
                Message message = new Message();
                message.setFromUserID(fromUserID);
                message.setToUserID(toUserID);
                message.setActualMassage(actualMessage);
                message.setDate(date);

                notifyUser.sendEmail("officeorganization15@gmail.com", toUserEmail, messageSubject, actualMessage);
                notifyUser.notify(fromUserEmail, toUserEmail, actualMessage);
                
                boolean status = daoMessage.insertMessage(message);
                
            }
            if (staffMembers.size() > 0){
                    out.print("<h2 style=\"color: #3366cc; text-align: center\">operation done successfully</h2>");
            }
            else{
                out.print("<h2 style=\"color: #3366cc; text-align: center\">operation failed!</h2>");
            }
            daoFinder.closeConnection();
            daoMessage.closeConnection();
            
            
        }catch (Exception e) {
           System.out.print("@@@@#### Error in MessageAllSubjectStaff @@@@####  " + e);
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