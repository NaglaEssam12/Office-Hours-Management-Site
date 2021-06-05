<%-- 
    Document   : index
    Created on : Dec 30, 2020, 6:35:41 PM
    Author     : User
--%>

<%@page import="controller.NotifyUser"%>
<%@page import="model.OfficeHours"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.DaoReservationTable"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>index</title>
        <link rel="stylesheet"  type="text/css" href="style.css" >
    </head>
    <body>
       
        
     <div class="container">

        <div id="signup">
        <div class="header">
        
            <h3>Who are you?</h3>

        </div>
        
                        
        <div class="sep"></div>

        <div class="inputs">
            
 
         <form action="signup_student.jsp" method="GET">
            <input id ="submit" type ="submit" value="SignUp As Student"> 
        </form>
        <form action="signup_staffmember.jsp" method="GET">
            <input id ="submit" type ="submit" value="SignUp As Staff member"> 
        </form>
        <form action="signin_user.jsp" method="GET">
            <input id ="submit" type ="submit" value="SignIn"> 
        </form>
            

       <!-- <form action="<%=request.getContextPath()%>/Notify" method="GET">
          
            <input id ="submit" type ="submit" value="Test Notify"> 
        </form>
        
       
        
        <form action="<%=request.getContextPath()%>/showMyNotifications" method="GET">
          
            <input id ="submit" type ="submit" value="Show My Notifications"> 
        </form>-->
        </div>


</div>
     </div>
        
        <%
            DaoReservationTable daoReservationTable = new DaoReservationTable();
            ArrayList<Integer> officeHoursIds = daoReservationTable.selectOfficeHoursIDs();
            ArrayList<OfficeHours> todayOfficeHours = daoReservationTable.selectTodayOfficeHours(officeHoursIds);
            ArrayList<Integer> fromUserIds = daoReservationTable.getFromUserIDs();
            System.out.println("** " + fromUserIds);
            NotifyUser notifyUser = new NotifyUser();
            
            // just for printing...
           for(int i = 0; i < todayOfficeHours.size(); ++i){
           System.out.println("* " + todayOfficeHours.get(i).getOfficeHoursID() );
           }
           
           for(int i = 0; i < fromUserIds.size(); ++i){
                  String notificationMessage = "We remember you that you have meeting today\n"; 

                  notifyUser.sendEmail("officeorganization15@gmail.com"// notify the student
                                       , daoReservationTable.getUserEmailByID(fromUserIds.get(i)) 
                                       , "Remember Meeting"
                                       , notificationMessage);

                  notifyUser.notify("officeorganization15@gmail.com"
                          , daoReservationTable.getUserEmailByID(fromUserIds.get(i))
                          , notificationMessage);
                }
                
           for(int i = 0; i < todayOfficeHours.size(); ++i){
                  String notificationMessage = "We remember you that you have meeting today\n";
                  notifyUser.sendEmail("officeorganization15@gmail.com" // notify the doctor
                                       , daoReservationTable.getUserEmailByID(todayOfficeHours.get(i).getUserID()) 
                                       , "Remember Meeting"
                                       , notificationMessage);

                  notifyUser.notify("officeorganization15@gmail.com"
                          , daoReservationTable.getUserEmailByID(todayOfficeHours.get(i).getUserID())
                          , notificationMessage);
            }          
            
        %> 
    </body>
</html>