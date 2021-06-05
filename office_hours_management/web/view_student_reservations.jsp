<%-- 
    Document   : view_student_reservations
    Created on : Jan 5, 2021, 6:56:57 PM
    Author     : DELL
--%>

<%@page import="model.Reservation"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.DaoSelectAll"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    Integer studentID = Integer.parseInt(session.getAttribute("userID").toString());
    DaoSelectAll daoSelectAll = new DaoSelectAll();

    ArrayList<Reservation> allStudentReservations = new ArrayList<Reservation>();
    allStudentReservations = daoSelectAll.selectAllStudentReservations(studentID);
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet"  type="text/css" href="style.css" >
        <title>JSP Page</title>
    </head>
    <body>
        <h1 style="color:red; text-align: center">Student Reservations Page</h1>
        <%if (allStudentReservations.size() > 0) {%>
        <div style="text-align: center; font-size: 18px;position: fixed;top: 30%;left: 50%;transform: translate(-50%, -50%);">
            <table border = "1px" style="float: center; text-align:center;font-size: 25px">
            <tr>
                <th>Reservation ID</th>
                <th>Office Hours ID</th>
                <th>To User ID</th>
            </tr>
            <%for (int i = 0; i < allStudentReservations.size(); i++) {%>
            <tr>
                <td><%=allStudentReservations.get(i).getReservationID()%></td>
                <td><%=allStudentReservations.get(i).getOfficeHoursID()%></td>
                <td><%=allStudentReservations.get(i).getToUserID()%></td>
                <td>
                    <form action="CancelReservationByStudent">
                        <input type="hidden" name="reservationID" value="<%=allStudentReservations.get(i).getReservationID()%>">
                        <input id="submit2" type="submit" name="cancel" value="Cancel">
                    </form>
                </td>
            </tr>
            <%}%>
        </table>
        </div>
        <%} else {%>
        <h2 style="color: #3366cc; text-align: center">There is no Reservations!</h2>
        <%}%>
    </body>
</html>
