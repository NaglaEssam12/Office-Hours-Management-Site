

<%@page import="dao.DaoFilteration"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="dao.DoaEditStaffMemberProfile"%>
<%@page import="dao.DaoOfficeHoursTable"%>
<%@page import="dao.DaoViewStaffMemberMessages"%>
<%@page import="model.OfficeHours"%>
<%@page import="java.util.*"%>
<%@page import="java.time.LocalDate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Staff Member Profile</title>
        <link rel="stylesheet"  type="text/css" href="style.css" >
        <!--<link rel="stylesheet"  type="text/css" href="staff_member_profile_style.css">-->
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>
    </head>
    <body>
        <script type="text/javascript">
            var counter = 0;
            $(document).ready(function () {
                $('#checkBtn1,#checkBtn2,#checkBtn3').click(function () {
                    checked = $("input[type=checkbox]:checked").length;

                    if (!checked) {
                        alert("You must check only one checkbox.");
                        return false;
                    }
                    if (checked > 1)
                    {
                        alert("You must check only one checkbox.");
                        return false;
                    }


                });
            });
            $(document).ready(function () {
                $('.form_submit').submit(function () {
                    $('.form_submit :input').each(function () {

                        if (($.trim($(this).val()).length === 0)) {
                            counter++;
                        }
                    });
                    //alert(counter);
                    if (counter >=6)
                    {
                        alert('Please, enter at least one field.');
                        return false;
                    }
                });



            });

        </script>
        <%
            DoaEditStaffMemberProfile doaEditStaffMemberProfile = new DoaEditStaffMemberProfile();
                    DaoViewStaffMemberMessages daoViewStaffMemberMessages = new DaoViewStaffMemberMessages();
                    ResultSet RS = null;
                    String email = request.getSession().getAttribute("email").toString();
                    String staffMemberID = daoViewStaffMemberMessages.getStaffMemberID(email);
                    RS = doaEditStaffMemberProfile.selectAllData(email);
                    ResultSet RS1 = null;
        %>
        <form id="signup1" action="<%=request.getContextPath()%>/SignOut" method="GET">
            <div class="inputs">
                <input id="submit1" type="submit" value="SignOut" style="float: right">
            </div>
        </form>


        <div class="container">
            <% if(request.getParameter("editProfile")!=null){%>
           
       
            <form class = "form_submit" id="signup" action="<%=request.getContextPath()%>/EditStaffMemberProfile" method="POST">
                <%
                    
                    String userName = "", displayName = "", email1 = "", password = "", subject = "", type = "";
                    while (RS.next()) {
                        userName = RS.getString("userName");
                        displayName = RS.getString("displayName");
                        email1 = RS.getString("email");
                        password = RS.getString("password");
                        subject = RS.getString("subjectName");
                        type = RS.getString("type");
                    }
                    doaEditStaffMemberProfile.closeConnection();
                %>

                <div class="header">

                    <h3> Edit Your Profile </h3>

                </div>

                <div class="sep"></div>

                <div class="inputs">
                    <strong>User Name</strong>
                    <input type="text" placeholder="user name" name="userName" autofocus disabled value=<%= userName%> />
                     <strong>Display Name</strong>
                    <input id="displayName" type="text" placeholder="Display Name"name="displayName" oninput="ajaxDiplayName()" value=<%= displayName%>  />  
                    <div id="error1" class="error1" hidden ></div>
                    <div id="success1" class="success1" hidden ></div>
                     <strong> Email</strong>
                    <input id="email" type="email" placeholder="e-mail"  name="email" oninput="ajaxEmail()" value=<%=email1%> />
                    <div id="error2" class="error2" hidden></div>
                    <div id="success2" class="success2" hidden ></div>
                     <strong> Password</strong>
                    <input type="text" placeholder="Password"name="password" value=<%= password%> />   
                     <strong> Subject</strong>
                    <input type="text" placeholder="Subject"name="subject" value=<%= subject%> />   
                     <strong> Type</strong>
                    <select name="type" class="select-css">
                        <%if (type.equals("TA")) {%>
                        <option value="TA"> TA </option>
                        <option value="Dr"> Dr </option>
                        <%} else {%>
                        <option value="Dr"> Dr </option>
                        <option value="TA"> TA </option>
                        <% }%>
                    </select> 

                    <input  id="submit" type="submit" value="Edit Profile">



                </div>

            </form>
            <%}%>
            <br><br>
            <% if(request.getParameter("viewMessage")!=null){%>
            <form id="signup" action="<%=request.getContextPath()%>/staffMemberViewMessages.jsp" method="GET">
                <div class="header">

                    <h3> Reply to & View Your Messages </h3>

                </div>

                <div class="sep"></div>

                <div class="inputs">
                    <input id="submit" type="submit" value="View Messages">
                </div>
            </form>
                <%}%>
                <br>
           
             <% if(request.getParameter("sendMessage1")!=null){%>
            <form id="signup" action="<%=request.getContextPath()%>/StaffMemberMessageSpecificUser"  method="GET">
                <div class="header">

                    <h3> Message Specific User </h3>

                </div>

                <div class="sep"></div>

                <div class="inputs">
                    <input list="displayNames" placeholder="Display Name" name="displayName" autofocus required />
                    <%
                        daoViewStaffMemberMessages = new DaoViewStaffMemberMessages();
                        
                        RS1 = daoViewStaffMemberMessages.selectColumns();%>
                    <datalist id = "displayNames">
                        <%
                            while (RS1.next()) {%>
                        <option value=<%= RS1.getString("displayName")%> >
                            <% }
                                daoViewStaffMemberMessages.closeConnection();
                            %>
                    </datalist>
                    <input type="text" placeholder="Message"name="message1" required/>    
                    <input id="submit" type="submit" value="Send Message">
                </div>    
            </form>
                    <%}%>
            <br>
             <% if(request.getParameter("sendMessage2")!=null){%>
            <form id="signup" action="<%=request.getContextPath()%>/StaffMemberMessageSpecificSubject"  method="GET">
                <div class="header">

                    <h3> Message Staff Member </h3>

                </div>

                <div class="sep"></div>

                <div class="inputs">
                    <input list="subjectNames" placeholder="Subject Name" name="subjectName" autofocus required />
                    <%
                        daoViewStaffMemberMessages = new DaoViewStaffMemberMessages();
                        RS1 = null;
                        RS1 = daoViewStaffMemberMessages.selectStaffColumns();%>
                    <datalist id = "subjectNames">
                        <%
                            ArrayList subjectNames = new ArrayList();
                            while (RS1.next()) {
                                if (!subjectNames.contains(RS1.getString("subjectName"))) {
                                    subjectNames.add((String) RS1.getString("subjectName"));
                                }
                            }
                            for (int i = 0; i < subjectNames.size(); i++) {

                        %>

                        <option value=<%= subjectNames.get(i)%> >
                            <% }

                                daoViewStaffMemberMessages.closeConnection();
                            %>
                    </datalist>
                    <!-- <input type="text" placeholder="Subject Name"name="subjectName" autofocus required /> -->
                    <input type="text" placeholder="Message"name="message1" required/>    
                    <input id="submit" type="submit" value="Send Message">
                </div>
            </form>
                    <%}%>
            <br>
             <% if(request.getParameter("search")!=null){%>
            <form id="signup" action="<%=request.getContextPath()%>/view_student_details.jsp" method="GET">
                <div class="header">

                    <h3> View Student Details </h3>

                </div>

                <div class="sep"></div>

                <div class="inputs">
                    <input list="displayNames" placeholder="Display Name" name="displayName" autofocus required />
                    <%
                        daoViewStaffMemberMessages = new DaoViewStaffMemberMessages();
                        RS1 = null;
                        RS1 = daoViewStaffMemberMessages.selectColumns();%>
                    <datalist id = "displayNames">
                        <%while (RS1.next()) {%>
                        <option value=<%= RS1.getString("displayName")%> >
                            <% }
                                daoViewStaffMemberMessages.closeConnection();
                            %>
                    </datalist>
                    <!--<input type="text" placeholder="Display Name"name="displayName" autofocus required/> -->
                    <input id="submit" type="submit" value="Search">
                </div>
            </form>
                    <%}%>
            <br>
             <% if(request.getParameter("viewReservations")!=null){%>
            <form id="signup" action="<%=request.getContextPath()%>/view_reservations.jsp" method="GET">
                <div class="header">

                    <h3> View Reservations </h3>

                </div>

                <div class="sep"></div>

                <div class="inputs">
                    <input list="From Time-To Time" placeholder="From Time-To Time "name="time" autofocus required /> 
                    <%
                        DaoOfficeHoursTable daoOfficeHoursTable = new DaoOfficeHoursTable();
                        DaoFilteration daoFilteration = new DaoFilteration();
                        RS1 = null;
                        RS1 = daoOfficeHoursTable.getSpecificSlots(staffMemberID);%>
                    <datalist id = "From Time-To Time">
                        <%
                            ArrayList Times = new ArrayList();
                            Times = daoFilteration.filterTimes(RS1);

                            for (int i = 0; i < Times.size(); i++) {

                        %>

                        <option value=<%= Times.get(i)%> >
                            <% }

                                daoOfficeHoursTable.closeConnection();
                            %>
                    </datalist>
                    <input list="dates" placeholder="Date "name="date" autofocus required /> 
                    <%
                        daoOfficeHoursTable = new DaoOfficeHoursTable();
                        RS1 = null;
                        RS1 = daoOfficeHoursTable.getSpecificSlots(staffMemberID);%>
                    <datalist id = "dates">
                        <%ArrayList dates = new ArrayList();
                            dates = daoFilteration.filterDates(RS1);

                            for (int i = 0; i < dates.size(); i++) {

                        %>

                        <option value=<%= dates.get(i)%> >
                            <% }
                                daoOfficeHoursTable.closeConnection();
                            %>
                    </datalist> 
                    <input id="submit" type="submit" value="View Reservations">
                </div>
            </form>
                    <%}%>
            
             <% if(request.getParameter("cancelReservations")!=null){%>
            <form id="signup" action="<%=request.getContextPath()%>/cancel_reservations.jsp" method="GET">
                <div class="header">

                    <h3> Cancel Reservations </h3>

                </div>

                <div class="sep"></div>

                <div class="inputs">
                    <input type="checkbox" name="slot"  /><strong style="font-size: 18px"> Cancel Specific Slot Reservations </strong><br>
                    <input type="checkbox" name="date" autofocus /><strong style="font-size: 18px"> Cancel Specific Day Reservations </strong><br>
                    <input id="checkBtn1" type="submit" value="Submit">
                </div>
            </form>
                <%}%>

            
             <% if(request.getParameter("viewReservationHistory")!=null){%>
            <form id="signup" action="<%=request.getContextPath()%>/view_reservation_history.jsp" method="GET">
                <div class="header">

                    <h3> View Reservation History </h3>

                </div>

                <div class="sep"></div>

                <div class="inputs">
                    <input type="checkbox" name="slot" value = "Slot" autofocus /><strong style="font-size: 18px"> Slot  </strong><br>
                    <input type="checkbox" name="date" value=" From date – To date" autofocus /><strong style="font-size: 18px">  From date - To date </strong><br>
                    <input id = "checkBtn2" type="submit" value="Submit">
                </div>
            </form>
                <%}%>
            
             <% if(request.getParameter("officeHoursManagement")!=null){%>
            <form id="signup" action="<%=request.getContextPath()%>/office_hours_forms.jsp" method="GET">
                <div class="header">

                    <h3> Office Hours Management </h3>

                </div>

                <div class="sep"></div>

                <div class="inputs">
                    <input type="checkbox" name="insert" autofocus /><strong style="font-size: 18px"> Insert</strong> <br>
                    <input type="checkbox" name="update" autofocus /><strong style="font-size: 18px"> Update </strong><br>
                    <input type="checkbox" name="delete" autofocus /><strong style="font-size: 18px"> Delete </strong><br>
                    <input type="checkbox" name="select" autofocus /><strong style="font-size: 18px"> Select </strong><br>
                    <input id = "checkBtn3" type="submit" value="Submit">
                </div>
            </form>   
                <%}%>


        </div>
        <script src="javascriptValidation.js"></script>
    </body>
</html>
