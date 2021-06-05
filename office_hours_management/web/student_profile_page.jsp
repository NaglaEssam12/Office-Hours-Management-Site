
<%@page import="model.Reservation"%>
<%@page import="model.User"%>
<%@page import="model.Subject"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.DaoSelectAll"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    DaoSelectAll daoSelectAll = new DaoSelectAll();

    ArrayList<Subject> allSubjects = new ArrayList<Subject>();
    allSubjects = daoSelectAll.selectAllSubjects();

    ArrayList<User> allStaffMembers = new ArrayList<User>();
    allStaffMembers = daoSelectAll.selectAllStaffMembers();

    daoSelectAll.closeConnection();
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Student profile Page</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <link rel="stylesheet"  type="text/css" href="student_profile_style.css" >
        <!-- Load an icon library -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    </head>
    <body>

        <div class ="studentProfile">

            <!-- Find Staff Member by Subject -->
            <div class="dropdown inputs">
                <input type="submit" id="submit" onclick="findStaffBySubjectButton()" class="dropbtn" value="              Find Staff Member by Subject         ">
                <div id="myDropdown1" class="dropdown-content"  style="padding:5px 5px 5px">
                    <input type="text" placeholder="Enter subject name.." id="myInput1" onkeyup="filterSubjects()">
                    <%  for (int i = 0; i < allSubjects.size(); i++) {
                            Integer subjectID = allSubjects.get(i).getSubjectID();
                            String subjectName = allSubjects.get(i).getSubjectName();%>
                    <form action="subject_staffmembers.jsp">
                        <input type="hidden" name="subjectID" value="<%= subjectID%>">
                        <input type="hidden" name="subjectName" value="<%=subjectName%>">
                        <a><%= subjectName%> <input id="submit2" type="submit" name="submit" value="Choose" style="float: right;"></a>
                    </form>
                    <% }%> 

                </div>
            </div>

            <!-- View Staff Member Contact -->
            <div class="dropdown inputs">
                <input type="submit" id="submit" onclick="viewStaffContactButton()" value="               View Staff Member Contact            ">
                <div id="myDropdown2" class="dropdown-content"  style="padding:5px 5px 5px">
                    <input type="text" placeholder="Enter staff member name.." id="myInput2" onkeyup="filterStaffMembers()">
                    <%  for (int i = 0; i < allStaffMembers.size(); i++) {
                            Integer staffMemberID = allStaffMembers.get(i).getUserID();
                            String staffMemberDisplyName = allStaffMembers.get(i).getUserDisplayName();
                            String staffMemberType = allStaffMembers.get(i).getUserType();%>
                    <form action="contact_of_staffMember.jsp">
                        <input type="hidden" name="staffMemberID" value="<%=staffMemberID%>">
                        <input type="hidden" name="staffMemberType" value="<%=staffMemberType%>">
                        <input type="hidden" name="staffMemberDisplayName" value="<%=staffMemberDisplyName%>">
                        <a><%= staffMemberDisplyName%> <input id="submit2" type="submit" name="submit" value="Choose" style="float: right;"></a>
                    </form>
                    <% }%> 

                </div>
            </div>

            <!-- Reserve Appointment -->
            <div class="dropdown">

                <input type="submit" id="submit" onclick="ReserveButton()" value="                  Reserve Appointment                  ">
                <div id="myDropdown5" class="dropdown-content"  style="padding:5px 5px 5px">
                    <input type="text" placeholder="Enter staff member name.." id="myInput5" onkeyup="filterStaffMembers3()">
                    <%  for (int i = 0; i < allStaffMembers.size(); i++) {
                            Integer staffMemberID = allStaffMembers.get(i).getUserID();
                            String staffMemberDisplyName = allStaffMembers.get(i).getUserDisplayName();
                            String staffMemberType = allStaffMembers.get(i).getUserType();%>
                    <form action="schedule_of_staffMember.jsp">
                        <input type="hidden" name="staffMemberID" value="<%=staffMemberID%>">
                        <input type="hidden" name="staffMemberType" value="<%=staffMemberType%>">
                        <input type="hidden" name="staffMemberDisplayName" value="<%=staffMemberDisplyName%>">
                        <a><%= staffMemberDisplyName%> <input id="submit2" type="submit" name="submit" value="Choose" style="float: right;"></a>
                    </form>
                    <% }%> 

                </div>
            </div>

            <!-- Send Message to Specific Staff Member -->
            <div class="dropdown">
                
                 <input type="submit" id="submit" onclick="viewStaffContactButton2()" value="     Send Message to Specific Staff Member    ">
                <div id="myDropdown3" class="dropdown-content"  style="padding:5px 5px 5px">
                    <input type="text" placeholder="Enter staff member name.." id="myInput3" onkeyup="filterStaffMembers2()">
                    <%  for (int i = 0; i < allStaffMembers.size(); i++) {
                            Integer staffMemberID = allStaffMembers.get(i).getUserID();
                            String staffMemberDisplyName = allStaffMembers.get(i).getUserDisplayName();
                            String staffMemberType = allStaffMembers.get(i).getUserType();%>
                    <form action="message_one_staffMember_form.jsp">
                        <input type="hidden" name="staffMemberID" value="<%=staffMemberID%>">
                        <input type="hidden" name="staffMemberType" value="<%=staffMemberType%>">
                        <input type="hidden" name="staffMemberDisplayName" value="<%=staffMemberDisplyName%>">
                        <a><%= staffMemberDisplyName%> <input id="submit2"type="submit" name="submit" value="Choose" style="float: right;"></a>
                    </form>
                    <% }%> 

                </div>
            </div>

            <!-- Send Message to all subject Staff Members -->
            <div class="dropdown">
               
                <input type="submit" id="submit" onclick="findStaffBySubjectButton2()" value="  Send Message to all subject Staff Members  ">
                <div id="myDropdown4" class="dropdown-content"  style="padding:5px 5px 5px">
                    <input type="text" placeholder="Enter subject name.." id="myInput4" onkeyup="filterSubjects2()">
                    <%  for (int i = 0; i < allSubjects.size(); i++) {
                            Integer subjectID = allSubjects.get(i).getSubjectID();
                            String subjectName = allSubjects.get(i).getSubjectName();%>
                    <form action="message_all_subject_staff_form.jsp">
                        <input type="hidden" name="subjectID" value="<%= subjectID%>">
                        <input type="hidden" name="subjectName" value="<%=subjectName%>">
                        <a><%= subjectName%> <input id="submit2"type="submit" name="submit" value="Choose" style="float: right;"></a>
                    </form>
                    <% }%> 

                </div>
            </div>


            <form action="view_student_reservations.jsp">
                <input id="submit" type="submit" name="submit" value="View my Reservations">
            </form>

            <form action="edit_student_profile_form.jsp">
                <input id="submit" type="submit" name="submit" value="Edit Profile">
            </form>
            <form id="showMyNotifications" action="<%=request.getContextPath()%>/showMyNotifications" method="GET">

                <input id="submit" type="submit" value="Show My Notifications">
            </form>
            <form id="SignOut" action="<%=request.getContextPath()%>/SignOut" method="GET">

                <input id="submit" type="submit" value="SignOut">
            </form>
            
        </div>
        <script>
            /* When the user clicks on the button,
             toggle between hiding and showing the dropdown content */
            function findStaffBySubjectButton() {
                document.getElementById("myDropdown1").classList.toggle("show");
            }

            function filterSubjects() {
                var input, filter, ul, li, a, i;
                input = document.getElementById("myInput1");
                filter = input.value.toUpperCase();
                div = document.getElementById("myDropdown1");
                a = div.getElementsByTagName("a");
                for (i = 0; i < a.length; i++) {
                    txtValue = a[i].textContent || a[i].innerText;
                    if (txtValue.toUpperCase().indexOf(filter) > -1) {
                        a[i].style.display = "";
                    } else {
                        a[i].style.display = "none";
                    }
                }
            }

            function findStaffBySubjectButton2() {
                document.getElementById("myDropdown4").classList.toggle("show");
            }

            function filterSubjects2() {
                var input, filter, ul, li, a, i;
                input = document.getElementById("myInput4");
                filter = input.value.toUpperCase();
                div = document.getElementById("myDropdown4");
                a = div.getElementsByTagName("a");
                for (i = 0; i < a.length; i++) {
                    txtValue = a[i].textContent || a[i].innerText;
                    if (txtValue.toUpperCase().indexOf(filter) > -1) {
                        a[i].style.display = "";
                    } else {
                        a[i].style.display = "none";
                    }
                }
            }

            function ReserveButton() {
                document.getElementById("myDropdown5").classList.toggle("show");
            }

            function filterStaffMembers3() {
                var input, filter, ul, li, a, i;
                input = document.getElementById("myInput5");
                filter = input.value.toUpperCase();
                div = document.getElementById("myDropdown5");
                a = div.getElementsByTagName("a");
                for (i = 0; i < a.length; i++) {
                    txtValue = a[i].textContent || a[i].innerText;
                    if (txtValue.toUpperCase().indexOf(filter) > -1) {
                        a[i].style.display = "";
                    } else {
                        a[i].style.display = "none";
                    }
                }
            }
            
            function viewStaffContactButton() {
                document.getElementById("myDropdown2").classList.toggle("show");
            }

            function filterStaffMembers() {
                var input, filter, ul, li, a, i;
                input = document.getElementById("myInput2");
                filter = input.value.toUpperCase();
                div = document.getElementById("myDropdown2");
                a = div.getElementsByTagName("a");
                for (i = 0; i < a.length; i++) {
                    txtValue = a[i].textContent || a[i].innerText;
                    if (txtValue.toUpperCase().indexOf(filter) > -1) {
                        a[i].style.display = "";
                    } else {
                        a[i].style.display = "none";
                    }
                }
            }

            function viewStaffContactButton2() {
                document.getElementById("myDropdown3").classList.toggle("show");
            }

            function filterStaffMembers2() {
                var input, filter, ul, li, a, i;
                input = document.getElementById("myInput3");
                filter = input.value.toUpperCase();
                div = document.getElementById("myDropdown3");
                a = div.getElementsByTagName("a");
                for (i = 0; i < a.length; i++) {
                    txtValue = a[i].textContent || a[i].innerText;
                    if (txtValue.toUpperCase().indexOf(filter) > -1) {
                        a[i].style.display = "";
                    } else {
                        a[i].style.display = "none";
                    }
                }
            }


        </script>
    </body>
</html>
