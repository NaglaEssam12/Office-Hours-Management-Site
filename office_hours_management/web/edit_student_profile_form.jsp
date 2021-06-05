<%-- 
    Document   : edit_student_profile_form
    Created on : Jan 6, 2021, 10:16:43 PM
    Author     : DELL
--%>

<%@page import="dao.DaoSelectAll"%>
<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<%
    DaoSelectAll daoSelectAll = new DaoSelectAll();
    User user = new User();
    String email = request.getSession().getAttribute("email").toString();
    user = daoSelectAll.selectAllUserData(email);
    String userName = "", displayName = "", password = "", department = "";
    userName = user.getUserName();
    displayName = user.getUserDisplayName();
    password = user.getUserPassword();
    department = user.getUserDepartment();

%>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Profile Page</title>
        <link rel="stylesheet"  type="text/css" href="style.css" >
    </head>
    <body>
        <div class="container">
            <form class = "form_submit" id="signup" action="<%=request.getContextPath()%>/EditStudentProfile" method="POST">
                <div class="header">

                    <h3> Edit Your Profile </h3>

                </div>

                <div class="sep"></div>
                <div class="inputs">
                    <strong>User Name</strong>
                    <input type="text" placeholder="user name" name="userName" autofocus disabled value=<%= userName%> />
                    
                    <strong>Display Name</strong>
                    <input required id="displayName" type="text" placeholder="Display Name"name="displayName" oninput="ajaxDiplayName()" value=<%= displayName%>  />  
                    <div id="error1" class="error1" hidden ></div>
                    <div id="success1" class="success1" hidden ></div>
                    <strong>Email</strong>
                    <input required id="email" type="email" placeholder="e-mail"  name="email" oninput="ajaxEmail()" value=<%=email%> />
                    <div id="error2" class="error2" hidden></div>
                    <div id="success2" class="success2" hidden></div>
                    
                    <strong>Password</strong>
                    <input required type="text" placeholder="Password"name="password" value=<%= password%> />  
                    
                    <strong>Department</strong>
                    <input required type="text" placeholder="Department"name="department"  value=<%= department%> /> 
                    
                    <input  id="submit" type="submit" value="Edit Profile">
                </div>
            </form>
        </div>
        <script src="javascriptValidation.js"></script>
    </body>
</html>
