<%-- 
    Document   : message_form
    Created on : Jan 6, 2021, 9:39:49 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    String ID = request.getParameter("staffMemberID");
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet"  type="text/css" href="style.css" >
        <script src="javascriptValidation.js"></script>
        <title>Message Form Page</title>
    </head>
    <body>
        <div class="container">
            <div id="signup">
                <div class="header">
                    <h1>Message Form Page</h1>
                </div>
                <div class="sep"></div>

                <div class="inputs">
                    <div id="changeHere">
                        <input id="messageSubject" type="text" name="messageSubject" placeholder="Message Subject" required>
                        <input id="messageContent" type="text" name="messageContent"placeholder="Content of Message" required>
                        <input id="toID" type ="hidden"name="toID" value=<%=ID%> >
                        
                        <input id="submit" type= "submit" value="Send" onclick="ajaxMessageResponse('MessageOneStaffMember')">
                    </div>
                </div>
            </div>
        </div>
    </body>

</html>