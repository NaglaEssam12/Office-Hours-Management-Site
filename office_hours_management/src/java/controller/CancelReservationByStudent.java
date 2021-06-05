/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DaoCancelResrvation;
import dao.DaoFinder;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DELL
 */
@WebServlet(name = "CancelReservationByStudent", urlPatterns = {"/CancelReservationByStudent"})
public class CancelReservationByStudent extends HttpServlet {

    DaoCancelResrvation daoCancelResrvation = new DaoCancelResrvation();
    DaoFinder daoFinder = new DaoFinder();
    NotifyUser notifyUser = new NotifyUser();
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
        ArrayList<Integer> fromIDToID = new ArrayList<>();
        
        Integer reservationID = Integer.parseInt(request.getParameter("reservationID"));
        try{
            fromIDToID = daoCancelResrvation.reservationFromIDToID(reservationID);
            boolean status = daoCancelResrvation.cancelReservation(reservationID);
            daoCancelResrvation.closeConnection();
            if (status == true){
                String fromUserEmail = request.getSession().getAttribute("email").toString();
                String toUserEmail = daoFinder.findContactForUser(fromIDToID.get(1));
                Integer studentID = fromIDToID.get(0);
                notifyUser.sendEmail("officeorganization15@gmail.com", toUserEmail, "Reservation Cancel", "Reservation cancel from student ID: " + studentID);
                notifyUser.notify(fromUserEmail, toUserEmail, "Reservation cancel from student ID: " + studentID);
                String htmlRespone = "<html>";
                htmlRespone +="<head> <link rel=\"stylesheet\"  type=\"text/css\" href=\"style.css\" ></head>";
                htmlRespone += "<body>";
                htmlRespone += "<h1 style=\"text-align:center\">cancel operation done successfully "+"</h1>";
                htmlRespone += "</body>";
                htmlRespone += "</html>";
                out.println(htmlRespone);
            }
            else{
                String htmlRespone = "<html>";
                htmlRespone +="<head> <link rel=\"stylesheet\"  type=\"text/css\" href=\"style.css\" ></head>";
                htmlRespone += "<body>";
                htmlRespone += "<h1 style=\"text-align:center\">cancel operation failed!"+"</h1>";
                htmlRespone += "</body>";
                htmlRespone += "</html>";
                out.println(htmlRespone);
            }
        }catch (Exception e) {
           System.out.print("@@@@#### Error in CancelReservationByStudent @@@@####  " + e);
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
