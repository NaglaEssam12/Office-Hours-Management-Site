/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DaoEditStudentProfile;
import dao.DoaEditStaffMemberProfile;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author ME
 */
@WebServlet(name = "EditStudentProfile", urlPatterns = {"/EditStudentProfile"})
public class EditStudentProfile extends HttpServlet {

    DoaEditStaffMemberProfile doaEditStaffMemberProfile = new DoaEditStaffMemberProfile();
    DaoEditStudentProfile daoEditStudentProfile = new DaoEditStudentProfile();

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

            String displayName = request.getParameter("displayName");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String department = request.getParameter("department");
            String userID = request.getSession().getAttribute("userID").toString();

            User user = new User();
            user.setUserDisplayName(displayName);
            user.setUserID(Integer.parseInt(userID));
            user.setUserEmail(email);
            user.setUserDepartment(department);
            user.setUserPassword(password);
            String emailTmp = request.getSession().getAttribute("email").toString();
            if (!email.equals("")) {
                HttpSession session = request.getSession(false);
                session.setAttribute("email", email);
            }

            daoEditStudentProfile.editStudentProfile(user);
            String htmlRespone = "<html>";
            htmlRespone += "<head> <link rel=\"stylesheet\"  type=\"text/css\" href=\"style.css\" ></head>";
            htmlRespone += "<body>";
            htmlRespone += "<h1 style=\"text-align:center\">You edit your profile successfully" + "</h1>";
            htmlRespone += "</body>";
            htmlRespone += "</html>";
            out.println(htmlRespone);
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
