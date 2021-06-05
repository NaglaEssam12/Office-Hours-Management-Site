<%-- 
    Document   : view_student_details
    Created on : Jan 2, 2021, 3:49:50 AM
    Author     : ME
--%>

<%@page import="dao.DaoViewStaffMemberMessages"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="dao.DaoViewStudentDetails"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link rel="stylesheet"  type="text/css" href="style.css" >
        <title>View Student Contact Details</title>
    </head>
    <body>
        <%
              DaoViewStudentDetails daoViewStudentDetails = new DaoViewStudentDetails();
              DaoViewStaffMemberMessages daoViewStaffMemberMessages = new DaoViewStaffMemberMessages();
              String displayName = request.getParameter("displayName");
              boolean flag = false;
              ResultSet RS1 = daoViewStaffMemberMessages.selectColumns();
            try {
                while(RS1.next())
                {
                    if(RS1.getString("displayName").equals(displayName) && RS1.getString("type").equals("student"))
                    {
                        flag = true;
                        System.out.println("displayName " + displayName);
                        ResultSet RS = daoViewStudentDetails.selectStudentDetails(displayName);
                        while(RS.next()){
                        %>
                        <h1 style="color:red; text-align: center">The Student contact details </h1>
                        <div style="text-align: center; font-size: 18px;position: fixed;top: 20%;left: 50%;transform: translate(-50%, -50%);">
                            <table border = "1px" style="float: center; text-align:center;font-size: 25px">
                                <tr> <td style="color:red">ID        </td><td> <%= RS.getString("userID")%><br></td></tr>
                                <tr> <td style="color:red">Name       </td><td><%=RS.getString("userName")%></td> <br></tr>
                                <tr> <td style="color:red">Email      </td><td><%= RS.getString("email")%> </td><br></tr>
                                <tr> <td style="color:red">Department</td><td> <%= RS.getString("department")%> </td><br></tr>
                            </table>
                        </div>
                       <%}
                
                        daoViewStudentDetails.closeConnection();
                    }
                    if(flag == true)
                    {
                        break;
                    }
                }
                daoViewStaffMemberMessages.closeConnection();
                if(flag == false){%>
                        <h1 style="text-align:center">You can't search about(invalid input / staff member)!!! </h1>
                     
                <%}
            } catch (Exception ex) {
                System.out.print(ex);
            }
                  
              
              
        %>
    </body>
</html>
