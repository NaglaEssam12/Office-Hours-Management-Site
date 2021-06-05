/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DaoChecker;
import dao.DaoOfficeHoursTable;
import dao.DaoViewStaffMemberMessages;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ME
 */
@WebServlet(name = "AjaxProcessSlot", urlPatterns = {"/AjaxProcessSlot"})
public class AjaxProcessSlot extends HttpServlet {
DaoChecker daoChecker = new DaoChecker() ;
DaoOfficeHoursTable daoOfficeHoursTable = new DaoOfficeHoursTable();
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
           try{
            String fromTime = request.getParameter("fromTime");
            String toTime = request.getParameter("toTime");
            String date = request.getParameter("date");
            DaoViewStaffMemberMessages daoViewStaffMemberMessages = new DaoViewStaffMemberMessages();
            String officeHoursID = ""; 
            String email = request.getSession().getAttribute("email").toString();
            String staffMemberID = daoViewStaffMemberMessages.getStaffMemberID(email);
            
            officeHoursID= daoOfficeHoursTable.selectOfficeHours(fromTime, toTime, date, staffMemberID);
            if(!officeHoursID.equals(""))
            {
                out.print("This Slot is already taken!!");
            }else{
             out.print("Done successfully");
            }

           
        }finally
        {
           out.close();
        }
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
