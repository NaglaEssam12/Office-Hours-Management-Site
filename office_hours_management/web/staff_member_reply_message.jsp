<%-- 
    Document   : StaffMemberReplyMessage
    Created on : Jan 1, 2021, 11:36:35 PM
    Author     : ME
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet"  type="text/css" href="style.css" >
        <title>Reply With A Message</title>
    </head>
    <body>
       <div class="container">
         <form id="signup" action="<%=request.getContextPath()%>/StaffMemberReplyMessage" id="my_captcha_form" method="GET">
             <div class="header">

                    <h3> Reply to a Message </h3>

                </div>

                <div class="sep"></div>

                <div class="inputs">
                <input type="text" placeholder="Message"name="message1" autofocus required />    
                <input id="submit" type="submit" value="Send Message">
                </div>
         </form>
       </div>
    </body>
</html>
