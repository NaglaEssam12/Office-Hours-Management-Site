/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DaoViewStaffMemberMessages;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Message;

/**
 *
 * @author ME
 */
@WebServlet(name = "StaffMemberMessageSpecificSubject", urlPatterns = {"/StaffMemberMessageSpecificSubject"})
public class StaffMemberMessageSpecificSubject extends HttpServlet {


    DaoViewStaffMemberMessages daoViewStaffMemberMessages = new DaoViewStaffMemberMessages();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String subjectName = request.getParameter("subjectName");  //The problem is still existed
            boolean flag = false;
            
            ResultSet RS = daoViewStaffMemberMessages.selectStaffColumns();
            try {
              
                while(RS.next())
                {
                    System.out.println("SubjectName "+ RS.getString("subjectName") + " " +subjectName);
                    if(RS.getString("subjectName").equals(subjectName))
                    {
                        String email = request.getSession().getAttribute("email").toString();
                        String fromID = daoViewStaffMemberMessages.getStaffMemberID(email);
                        String subjectNameTmp = daoViewStaffMemberMessages.getSpecificSubjectName(fromID);
                        flag = true;
                        if(subjectNameTmp.equals(subjectName)){
                            
                        String message = request.getParameter("message1");
                        
                   
                        System.out.println("Email " + email);
                        
                         System.out.println("FromId " + fromID);
                        ArrayList<String>toIDs =new ArrayList<>();
                                toIDs = daoViewStaffMemberMessages.getSpecificSubjectToIDs(subjectName,fromID);
                        LocalDate date = LocalDate.now(); 
                         Message messg;
                         
                        if(toIDs.size() == 0)
                        {
                            String htmlRespone = "<html>";
                            htmlRespone += "<head> <link rel=\"stylesheet\"  type=\"text/css\" href=\"style.css\" ></head>";
                            htmlRespone += "<body>";
                            htmlRespone += "<br><br><h1 style=\"text-align:center\">You can't send message !!!" + "</h1>";
                            htmlRespone += "</body>";
                            htmlRespone += "</html>";

                            out.println(htmlRespone);
                            
                        }
                        else
                        {
                           String htmlRespone = "<html>";
                           htmlRespone += "<head> <link rel=\"stylesheet\"  type=\"text/css\" href=\"style.css\" ></head>";
                           htmlRespone += "<body>";
                           htmlRespone += "<br><br><h1 style=\"text-align:center\">You have sent message successfully" + "</h1>";
                           htmlRespone += "</body>";
                           htmlRespone += "</html>";

                           out.println(htmlRespone); 
                        }
                        for(int i = 0; i < toIDs.size(); i++)
                        {
                            messg = new Message();
                            messg.setToUserID(Integer.parseInt(toIDs.get(i)));
                            messg.setFromUserID(Integer.parseInt(fromID));
                            messg.setActualMassage(message);
                            messg.setDate(date);
                           daoViewStaffMemberMessages.insertMessage(messg);
                        }
                       
                    }
                        else
                        {
                            String htmlRespone = "<html>";
                            htmlRespone += "<head> <link rel=\"stylesheet\"  type=\"text/css\" href=\"style.css\" ></head>"; 
                            htmlRespone += "<body>";
                            htmlRespone += "<br><br><h3 style=\"text-align:center\">You can't send message As you aren't staffmember of this subject!!!" + "</h3>";
                            htmlRespone += "</body>";
                            htmlRespone += "</html>";
                        
                            out.println(htmlRespone);
                        }
                    }
                    if(flag == true)
                    {
                        break;
                    }
                }
                daoViewStaffMemberMessages.closeConnection();
                if(flag == false){
                        String htmlRespone = "<html>";
                        htmlRespone +="<head> <link rel=\"stylesheet\"  type=\"text/css\" href=\"style.css\" ></head>";
                        htmlRespone += "<body>";
                        htmlRespone += "<br><br><h1 style=\"text-align:center\">You can't send message!!!" + "</h1>";
                        htmlRespone += "</body>";
                        htmlRespone += "</html>";
                        
                        out.println(htmlRespone);
                }
            } catch (Exception ex) {
                System.out.println("ssssssssssssss");
                 System.out.print("###################Error in data base:" + ex);
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
