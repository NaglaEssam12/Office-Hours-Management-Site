<%-- 
    Document   : staffMemberViewMessages
    Created on : Jan 1, 2021, 11:21:26 PM
    Author     : ME
--%>

<%@page import="dao.DaoViewStudentDetails"%>
<%@page import="java.sql.*"%>
<%@page import="dao.DaoViewStaffMemberMessages"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet"  type="text/css" href="style.css" >
        <title>Received Messages</title>
    </head>
    <body>
        <%
              DaoViewStaffMemberMessages daoViewStaffMemberMessages = new DaoViewStaffMemberMessages();
              DaoViewStudentDetails daoViewStudentDetails = new DaoViewStudentDetails();
              String email = request.getSession().getAttribute("email").toString();
              System.out.println("Email" + email);
              String ID = daoViewStaffMemberMessages.getStaffMemberID(email);
              System.out.println("ID"+ID);
              ResultSet RS = daoViewStaffMemberMessages.selectMessages(ID);
              ResultSet RS3 = null;
              boolean flag = false;
        %>
              <ol class = "list1">
              <%while(RS.next()){
                  RS3 = daoViewStudentDetails.selectStudentDetailsByID(RS.getString("fromUserID"));
                  flag = true;
                  while(RS3.next()){
              %>
                <li>
                    Message Number <a href="ViewStaffMemberMessages?method=GET&message=<%= RS.getString("messageID")%>"> <%= RS.getString("messageID")%></a> 
                    ,You received it from student Name <%=RS3.getString("displayName")%> and its content is: <%= RS.getString("actualMassage")%> in <%= RS.getString("date")%> <br>
                    </li>
             <%}
            }
             %>
              </ol>
              
                
              <%
                  if(flag == false)
                  {%>
                        <h1 style="text-align: center"> You don't have reservations </h1>
                  <%}
                daoViewStaffMemberMessages.closeConnection();
                daoViewStudentDetails.closeConnection();
                
                  
              
              
        %>
    </body>
</html>
