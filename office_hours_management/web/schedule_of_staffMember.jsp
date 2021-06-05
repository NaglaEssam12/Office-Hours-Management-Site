<%-- 
    Document   : schedule_of_staffMember
    Created on : Jan 5, 2021, 4:35:19 PM
    Author     : DELL
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="model.OfficeHours"%>
<%@page import="dao.DaoViewOfficeHours"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    Integer staffMemberID = Integer.parseInt(request.getParameter("staffMemberID").toString());
    String staffMemberDisplyName = request.getParameter("staffMemberDisplayName").toString();
    String staffMemberType = request.getParameter("staffMemberType").toString();

    ArrayList<OfficeHours> officeHourses = new ArrayList<OfficeHours>();
    DaoViewOfficeHours daoViewOfficeHours = new DaoViewOfficeHours();
    officeHourses = daoViewOfficeHours.findScheduleByStaffID(staffMemberID);
%> 

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet"  type="text/css" href="style.css" >
        <title>Office Hours page</title>
    </head>
    <body>
        <h1 style="color:red; text-align: center"><%=staffMemberType%>. <%=staffMemberDisplyName%> Schedule</h1>

        <%if (officeHourses.size() > 0) {%>
        <div style="text-align: center; font-size: 18px;position: fixed;top: 30%;left: 50%;transform: translate(-50%, -50%);">
            <table border = "1px" style="float: center; text-align:center;font-size: 25px">
                <tr>
                    <th>Office Hours ID</th>
                    <th>From Time</th>
                    <th>To Time</th>
                    <th>Date</th>
                    <th>State</th>
                    <th>Location</th>
                </tr>
                <%for (int i = 0; i < officeHourses.size(); i++) {%>
                <tr>
                    <td><%=officeHourses.get(i).getOfficeHoursID()%></td>
                    <td><%=officeHourses.get(i).getFromTime()%></td>
                    <td><%=officeHourses.get(i).getToTime()%></td>
                    <td><%=officeHourses.get(i).getDate()%></td>
                    <td><%=officeHourses.get(i).getState()%></td>
                    <td><%=officeHourses.get(i).getLocation()%></td>
                    <td>
                        <form action="ReserveAppointment">
                            <input type="hidden" name="staffMemberID" value="<%=staffMemberID%>">
                            <input type="hidden" name="slotID" value="<%=officeHourses.get(i).getOfficeHoursID()%>">
                            <input id="submit2" type="submit" name="reserve" value="Reserve">
                        </form>
                    </td>
                </tr>
                <%}%>
            </table>
        </div>
        <%} else {%>
        <h2 style="color: #3366cc; text-align: center">There is no Office Hours Available!</h2>
        <%}%>
    </body>
</html>
