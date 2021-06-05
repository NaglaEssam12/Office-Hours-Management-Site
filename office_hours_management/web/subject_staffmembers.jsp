<%-- 
    Document   : subject_staffmembers
    Created on : Jan 3, 2021, 7:52:11 PM
    Author     : DELL
--%>

<%@page import="dao.DaoFinder"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    Integer subjectID = Integer.parseInt(request.getParameter("subjectID").toString());
    String subjectName = request.getParameter("subjectName").toString();

    DaoFinder daoFinder = new DaoFinder();
    ArrayList<User> staffMembers = new ArrayList<User>();
    User user = new User();
    user.setUserDisplayName("mona");
    //staffMembers.add(user);
    staffMembers = daoFinder.findStaffBySubject(subjectID);
    daoFinder.closeConnection();
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet"  type="text/css" href="style.css" >
        <title>JSP Page</title>
    </head>
    <body>
        <h1 style="color:red; text-align: center">Staff Members of <%=subjectName%></h1>

        <%if (staffMembers.size() > 0) {%>
        <div style="text-align: center; font-size: 18px;position: fixed;top: 30%;left: 50%;transform: translate(-50%, -50%);">
        <table border = "1px" style="float: center; text-align:center;font-size: 25px">
          
                <tr>
                    <th>Staff Member</th>
                </tr>
                <%for (int i = 0; i < staffMembers.size(); i++) {%>
                <tr>
                    <td><%=staffMembers.get(i).getUserType()%>. 
                        <%=staffMembers.get(i).getUserDisplayName()%>
                    </td>
                    <td>
                        <form action="contact_of_staffMember.jsp">
                            <input type="hidden" name="staffMemberID" value="<%=staffMembers.get(i).getUserID()%>">
                            <input type="hidden" name="staffMemberType" value="<%=staffMembers.get(i).getUserType()%>">
                            <input type="hidden" name="staffMemberDisplayName" value="<%=staffMembers.get(i).getUserDisplayName()%>">
                            <input id="submit2" type="submit" name="subjectName" value="Find Contact">
                        </form>
                    </td>
                    <td>
                        <form action="schedule_of_staffMember.jsp">
                            <input type="hidden" name="staffMemberID" value="<%=staffMembers.get(i).getUserID()%>">
                            <input type="hidden" name="staffMemberType" value="<%=staffMembers.get(i).getUserType()%>">
                            <input type="hidden" name="staffMemberDisplayName" value="<%=staffMembers.get(i).getUserDisplayName()%>">
                            <input id="submit2" type="submit" name="subjectName" value="View office Hours Schedule">
                        </form>
                    </td>
                </tr>
                <%}%>
        </table>
        </div>
        <%} else {%>
        <h2 style="color: #3366cc; text-align: center">There is no Staff Members in This Subject!</h2>
        <%}%>
    </body>
</html>
