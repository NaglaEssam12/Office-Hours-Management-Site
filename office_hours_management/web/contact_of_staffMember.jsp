<%-- 
    Document   : contactOfStaffMember
    Created on : Jan 5, 2021, 4:13:07 PM
    Author     : DELL
--%>

<%@page import="dao.DaoFinder"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    Integer staffMemberID = Integer.parseInt(request.getParameter("staffMemberID").toString());
    String staffMemberDisplyName = request.getParameter("staffMemberDisplayName").toString();
    String staffMemberType = request.getParameter("staffMemberType").toString();

    DaoFinder daoFinder = new DaoFinder();
    String staffMemberEmail = daoFinder.findContactForUser(staffMemberID);
%>    
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet"  type="text/css" href="style.css" >
        <title>staff Member Contact Page</title>
    </head>
    <body>
        <h1 style="color:red; text-align: center">Contact Page</h1>

        <div style="text-align: center; font-size: 18px;position: fixed;top: 30%;left: 50%;transform: translate(-50%, -50%);">
        <table border = "1px" style="float: center; text-align:center;font-size: 25px">
                <tr>
                    <th>Staff Member</th>
                    <th>Contact</th>
                </tr>
                <tr>
                    <td><%=staffMemberType%>. 
                        <%=staffMemberDisplyName%>
                    </td>
                    <td>
                        <%=staffMemberEmail%>
                    </td>
                    <td>
                        <form action="schedule_of_staffMember.jsp">
                            <input type="hidden" name="staffMemberID" value="<%=staffMemberID%>">
                            <input type="hidden" name="staffMemberType" value="<%=staffMemberType%>">
                            <input type="hidden" name="staffMemberDisplayName" value="<%=staffMemberDisplyName%>">
                            <input id="submit2" type="submit" name="subjectName" value="View office Hours Schedule">
                        </form>
                    </td>
                </tr>
        </table>
        </div>
    </body>
</html>
