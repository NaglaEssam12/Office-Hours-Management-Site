<%-- 
    Document   : go_to_email_page
    Created on : Dec 30, 2020, 7:08:26 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link rel="stylesheet"  type="text/css" href="staff_member_profile_style.css" >
        <title>Staff Member Home Page</title>
    </head>
    <body>
        <div class="studentProfile">
        <form  action = "staff_member_profile_page.jsp" method="GET">
            <div class="inputs">
            <input  id="submit" type="submit" value="Edit Profile" name="editProfile">
            <input id="submit" type="submit" value="View Messages" name="viewMessage">
            <input id="submit" type="submit" value="Message Specific User"name="sendMessage1">
            <input id="submit" type="submit" value="Message Staff Member" name="sendMessage2">
            <input id="submit" type="submit" value="View Student Details" name="search">
            <input id="submit" type="submit" value="View Reservations" name="viewReservations">
            <input id="checkBtn1" type="submit" value="Cancel Reservations" name="cancelReservations">
            <input id="checkBtn2" type="submit" value="View Reservation History" name="viewReservationHistory">
            <input id="checkBtn3" type="submit" value="Office Hours Management" name="officeHoursManagement">
            
             
         
            </div>
        </form>
        <form id="showMyNotifications" action="<%=request.getContextPath()%>/showMyNotifications" method="GET">
               <div class="inputs">
                    <input id="submit" type="submit" value="Show My Notifications">
               </div>
            </form> 
        <form id="signup1" action="<%=request.getContextPath()%>/SignOut" method="GET">
           <div class="inputs">
               <input id="submit" type="submit" value="SignOut" style="float: top;">
           </div>
        </form>
        
    </div>
       
        
    </body>
</html>
