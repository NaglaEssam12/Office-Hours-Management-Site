/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DaoSelectAll;
import dao.DaoSignUpStaffMember;
import dao.DaoViewStaffMemberMessages;
import database_connection.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.nio.charset.Charset;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author User
 */
@WebServlet(name = "SignUpStaffMember", urlPatterns = {"/SignUpStaffMember"})
public class SignUpStaffMember extends HttpServlet {

    DaoSignUpStaffMember daoSignUpStaffMember= new  DaoSignUpStaffMember();
    DaoSelectAll daoSelectAll = new DaoSelectAll();
    NotifyUser notifyUser = new NotifyUser();
    DaoViewStaffMemberMessages daoViewStaffMemberMessages = new DaoViewStaffMemberMessages();
    
    public String generatePassword(){
        
        int n = 20;
        // length is bounded by 256 Character 
        byte[] array = new byte[256]; 
        new Random().nextBytes(array); 
  
        String randomString 
            = new String(array, Charset.forName("UTF-8")); 
  
        // Create a StringBuffer to store the result 
        StringBuffer r = new StringBuffer(); 
  
        // Append first 20 alphanumeric characters 
        // from the generated random String into the result 
        for (int k = 0; k < randomString.length(); k++) { 
  
            char ch = randomString.charAt(k); 
  
            if (((ch >= 'a' && ch <= 'z') 
                 || (ch >= 'A' && ch <= 'Z') 
                 || (ch >= '0' && ch <= '9')) 
                && (n > 0)) { 
  
                r.append(ch); 
                n--; 
            } 
        } 
  
        // return the resultant string 
        return r.toString(); 

    }
 
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");   
        try (PrintWriter out = response.getWriter()) {
        
  System.out.print("@@@@#### " + "Tro tro Tro DONE!!");
        User user = new User();   
        user.setUserName(request.getParameter("userName"));
        user.setUserDisplayName(request.getParameter("displayName"));
        user.setUserEmail(request.getParameter("email"));
        user.setUserType(request.getParameter("type"));
        user.setUserSubject(request.getParameter("subject"));
        
       String  password = generatePassword();
       
        while(daoSignUpStaffMember.isPasswordExisted(password))
        {
             password = generatePassword();
        }      
        user.setUserPassword(password);
       
        
        if(daoSignUpStaffMember.signUp(user))
        { 
            
             notifyUser.sendEmail("officeorganization15@gmail.com",user.getUserEmail(),"office hours management password",
                   "Hello, "+user.getUserName()+ "\n" +"Your passwrd is: " + password);
           String subjectID = daoSignUpStaffMember.getAllSubjectNames(user.getUserSubject());
           if(subjectID.equals(""))
           {
               daoSignUpStaffMember.insertSubjectName(user.getUserSubject());
               
           }
           subjectID = daoSignUpStaffMember.getAllSubjectNames(user.getUserSubject());
           String staffMemberID = daoViewStaffMemberMessages.getStaffMemberID(user.getUserEmail());
           daoSignUpStaffMember.insertStaffMemberSubject(staffMemberID, subjectID);
           String htmlRespone = "<html>";
            htmlRespone +="<head> <link rel=\"stylesheet\"  type=\"text/css\" href=\"style.css\" ></head>";
            htmlRespone += "<body>";
            htmlRespone += "<h1 style=\"text-align:center\">Your account Created successfully "+"</h1>";
            htmlRespone += "<h1 style=\"text-align:center\">Check Your email to get the password of your account..."+"</h1>";
            htmlRespone += "<br><br> <a href=\"signin_user.jsp\"><input type=\"submit\" value = \"Go To SignIn Page\"/>\n" +"</a>";
            htmlRespone += "</body>";
            htmlRespone += "</html>";
            out.println(htmlRespone);
           
           //response.sendRedirect("go_to_email_page.jsp");
           
        }
        } catch (Exception e) {
           System.out.print("@@@@#### " + e);
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

