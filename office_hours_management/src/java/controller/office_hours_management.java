/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DaoOfficeHoursTable;
import dao.DaoViewStaffMemberMessages;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.OfficeHours;

/**
 *
 * @author ME
 */
@WebServlet(name = "office_hours_management", urlPatterns = {"/office_hours_management"})
public class office_hours_management extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public OfficeHours getInputs(HttpServletRequest request, OfficeHours officeHours) {
        DaoViewStaffMemberMessages daoViewStaffMemberMessages = new DaoViewStaffMemberMessages();
        String fromTime = request.getParameter("fromTime");
        String toTime = request.getParameter("toTime");
        String date = request.getParameter("date");
        String state = request.getParameter("state");
        String location = request.getParameter("location");
        String email = request.getSession().getAttribute("email").toString();
        String userID = daoViewStaffMemberMessages.getStaffMemberID(email);
        // String userID = request.getParameter("userID");
        officeHours.setFromTime(fromTime);
        officeHours.setToTime(toTime);
        officeHours.setDate(LocalDate.parse(date));
        officeHours.setState(state);
        officeHours.setLocation(location);
        officeHours.setUserID(Integer.parseInt(userID));
        officeHours.setSent("false");
        return officeHours;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            OfficeHours officeHours = new OfficeHours();
            DaoOfficeHoursTable daoOfficeHoursTable = new DaoOfficeHoursTable();
            if (request.getParameter("button1") != null) {
                officeHours = getInputs(request, officeHours);
                String fromTime = officeHours.getFromTime();
                String toTime = officeHours.getToTime();
                LocalDate date = officeHours.getDate();
                int userID = officeHours.getUserID();
                String officeHoursID = daoOfficeHoursTable.selectOfficeHours(fromTime, toTime, date.toString(), userID + "");
                if (!officeHoursID.equals("")) {
                    out.println("<head> <link rel=\"stylesheet\"  type=\"text/css\" href=\"style.css\" ></head>");
                    out.println("<br><br><h1 style=\"text-align:center\">You Can't Insert this Query!!!</h1>");
                } else {
                    daoOfficeHoursTable.insertData(officeHours);
                    String htmlRespone = "<html>";
                    htmlRespone += "<body>";
                    htmlRespone += "<head> <link rel=\"stylesheet\"  type=\"text/css\" href=\"style.css\" ></head>";
                    htmlRespone += "<br><br><h1 style=\"text-align:center\">You insert the data successfully" + "</h1>";
                    htmlRespone += "</body>";
                    htmlRespone += "</html>";

                    out.println(htmlRespone);

                }
            }
            if (request.getParameter("button2") != null) {
                String officeHoursID = request.getParameter("officeHoursID");
                ResultSet RS = null;  
                boolean flag = false;
                RS = daoOfficeHoursTable.selectAllData();
                try {
                    while(RS.next()){
                        if(officeHoursID.equals(RS.getString("officeHoursID")))
                        {
                            flag = true;
                            break;
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(office_hours_management.class.getName()).log(Level.SEVERE, null, ex);
                }
                if(flag){   
                    officeHours = getInputs(request, officeHours);
                    String fromTime = officeHours.getFromTime();
                    String toTime = officeHours.getToTime();
                    LocalDate date = officeHours.getDate();
                    int userID = officeHours.getUserID();
                    String officeHoursID1 = daoOfficeHoursTable.selectOfficeHours(fromTime, toTime, date.toString(), userID + "");
                    if (!officeHoursID1.equals("")) {
                        out.println("<head> <link rel=\"stylesheet\"  type=\"text/css\" href=\"style.css\" ></head>");
                        out.println("<br><br><h1 style=\"text-align:center\">You Can't Update this Query!!!</h1>");
                    } else {
                        daoOfficeHoursTable.updateDate(officeHoursID, officeHours);
                        String htmlRespone = "<html>";
                        htmlRespone += "<head> <link rel=\"stylesheet\"  type=\"text/css\" href=\"style.css\" ></head>";
                        htmlRespone += "<body>";
                        htmlRespone += "<br><br><h1 style=\"text-align:center\">You update the data successfully" + "</h1>";
                        htmlRespone += "</body>";
                        htmlRespone += "</html>";

                        out.println(htmlRespone);
                    }
                }
                else
                {
                   String htmlRespone = "<html>";
                        htmlRespone += "<head> <link rel=\"stylesheet\"  type=\"text/css\" href=\"style.css\" ></head>";
                        htmlRespone += "<body>";
                        htmlRespone += "<br><br><h1 style=\"text-align:center\">You Can't update the data " + "</h1>";
                        htmlRespone += "</body>";
                        htmlRespone += "</html>";

                        out.println(htmlRespone); 
                }
            
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
