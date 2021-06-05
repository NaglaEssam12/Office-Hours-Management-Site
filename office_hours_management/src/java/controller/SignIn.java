
package controller;

import dao.DaoSelectAll;
import dao.DaoSignInUser;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;


@WebServlet(name = "signIn", urlPatterns = {"/signIn"})
public class SignIn extends HttpServlet {

    DaoSignInUser daoSignInUser = new DaoSignInUser();
    DaoSelectAll daoSelectAll = new DaoSelectAll();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
               
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = new User();
        user.setUserEmail(email);
        user.setUserPassword(password);
           
        try {
              
            if (daoSignInUser.signIn(user)) {
                
                System.out.print("################### sign in done sucessfully");
                HttpSession session = request.getSession();
                session.setAttribute("email",email);
                Integer userID = daoSelectAll.getUserIDByEmail(email);
                session.setAttribute("userID",userID);
                
               if(daoSignInUser.isUserStudent(user))
               {
                    response.sendRedirect("student_profile_page.jsp");
               }else{
              
                     response.sendRedirect("staff_member_home_page.jsp");
               }
               
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("error","You are not in the system!");
                
                response.sendRedirect("signin_user.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
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
