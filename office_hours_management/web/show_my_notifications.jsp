<%-- 
    Document   : show_my_notifications
    Created on : Jan 12, 2021, 11:28:53 AM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet"  type="text/css" href="style.css" >
        <title>Show Notification Page!</title>
    </head>
    <body>
        <h1 style="color:red; text-align: center">Show Notification Page!</h1>
        <%
           
            String output = session.getAttribute("output").toString();
            String[] outputSplitted = output.split("##");%>
            <table border="1px" style="text-align: center; font-size: 25px;position:absolute;top:30%;left: 50%;transform: translate(-50%, -50%);">
            <%for(int i = 0; i < outputSplitted.length ; ++i)
            {%>
            <tr>
                <td><%=outputSplitted[i]%></td>
            </tr>
                
                
           <% }%>
            </table>
        
    </body>
</html>
