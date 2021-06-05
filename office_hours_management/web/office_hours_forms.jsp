<%-- 
    Document   : office_hours_forms
    Created on : Jan 4, 2021, 1:27:19 AM
    Author     : ME
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet"  type="text/css" href="style.css" >
        <title>Office Hours Forms</title>
        <script src="javascriptValidation.js"></script>
    </head>
    <body>
        <%

            if (request.getParameter("insert") != null) {%>
            <div class="container">
                <form id="signup" action="office_hours_management" id="my_captcha_form" method="GET">
                    <div class="header">

                    <h3> Insert a new Data </h3>

                </div>

                <div class="sep"></div>

                <div class="inputs">

                    <input id="fromTime" type="text" placeholder="From Time (HH:MM) " name="fromTime" autofocus required/><br>

                    <input id="toTime" type="text" placeholder="To Time (HH:MM)" name="toTime" required /><br>  
                    <input id="date" type="text" placeholder="Date (YYYY-MM-DD)"  name="date" required oninput="ajaxSlot()" /><br>
                    <div id="error2" class="error2" hidden></div>
                    <div id="success2" class="success2" hidden></div>
                    <select name="state" class="select-css">
                        
                            <option value="offline"> Offline </option>
                            <option value="online"> Online </option>
                            
                    </select> <br>
                    <input type="text" placeholder="Location" name="location"required  /><br> 
                    <input id="submit" name = "button1" type="submit" value="Insert">
                </div>
                    
                </form> 
            </div>
        <% }
            if (request.getParameter("update") != null) {%>
            <div class="container">
                <form id="signup" action="office_hours_management" id="my_captcha_form" method="GET">
                    <div class="header">

                    <h3> Update an existed Data </h3>

                </div>

                <div class="sep"></div>

                <div class="inputs">
                    <input type="text" placeholder="OfficeHours ID" name="officeHoursID" required /><br>
                    <input id="fromTime" type="text" placeholder="From Time (HH:MM) " name="fromTime" autofocus required/><br>
                    <input id="toTime" type="text" placeholder="To Time (HH:MM)" name="toTime" required /><br>  
                    <input id="date" type="text" placeholder="Date (YYYY-MM-DD)"  name="date" required oninput="ajaxSlot()" /><br>
                    <div id="error2" class="error2" hidden></div>
                    <div id="success2" class="success2" hidden></div>
                     <select name="state" class="select-css">
                        
                            <option value="offline"> Offline </option>
                            <option value="online"> Online </option>
                            
                     </select> <br>
                    <input type="text" placeholder="Location" name="location"required  />  <br> 
                    <input id="submit" name = "button2" type="submit" value="Update">
                </div>
                </form> 
            </div>
        <%}
            if (request.getParameter("delete") != null) {%>
            <div class="container">
                <form id="signup" action="manage_office_hours.jsp" id="my_captcha_form" method="GET">
                    <div class="header">

                    <h3> Delete a Record </h3>

                </div>

                <div class="sep"></div>

                <div class="inputs">
                    <input type="text" placeholder="OfficeHours ID" name="officeHoursID"  autofocus required/>  <br> 
                    <input id="submit" name = "button3" type="submit" value="Delete">
                </div>
                </form> 
            </div>
        <% }
            if (request.getParameter("select") != null) {%>
            <div class="container">
                <form id="signup" action="manage_office_hours.jsp" id="my_captcha_form" method="GET"><div class="header">

                    <h3> Select All Data </h3>

                </div>

                <div class="sep"></div>

                <div class="inputs">
                    <input id="submit" name = "button4" type="submit" value="Select">
                </div>
                </form> 
            </div>
        <%}


        %>
       
    </body>
</html>
