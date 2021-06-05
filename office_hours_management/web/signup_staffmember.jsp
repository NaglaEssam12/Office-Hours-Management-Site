

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="controller.SignUpStaffMember"%>

<html>
    <head>
        <title>Sign up as a staff</title>
        <script  src="https://www.google.com/recaptcha/api.js" async defer></script>

        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet"  type="text/css" href="style.css" >
    </head>
    <body>

        <div class="container">

            <form id="signup" action="https://www.SnapHost.com/captcha/send.aspx" method="post" id="myform" target="_top">
                <input type="hidden" id="skip_WhereToSend" name="skip_WhereToSend"
                       value="mona76115@email.com" />
                <input type="hidden" id="skip_Subject" name="skip_Subject"
                       value="SnapHost.com script" />
                <input type="hidden" id="skip_WhereToReturn" name="skip_WhereToReturn"
                       value="http://localhost:8080/office_hours_management/SignUpStaffMember" />
                <input type="hidden" id="skip_SnapHostID" name="skip_SnapHostID"
                       value="36MPYKALZ6T5" />
                <div class="header">

                    <h3>Sign Up As A Staff Member</h3>

                </div>

                <div class="sep"></div>

                <div class="inputs">

                    <input type="text" placeholder="user name" name="userName" autofocus required/>

                    <input id="displayName" type="text" placeholder="Display Name"name="displayName" oninput="ajaxDiplayName()" required/>  
                    <div id="error1" class="error1" hidden ></div>
                    <div id="success1" class="success1" hidden ></div>
                    <input id="email" type="email" placeholder="e-mail"  name="email" oninput="ajaxEmail()" required/>
                    <div id="error2" class="error2" hidden></div>
                    <div id="success2" class="success2" hidden></div>

                    <input type="text" placeholder="Subject"name="subject" required/>   
                    <select name="type" class="select-css" required>       
                        <option value="TA"> TA </option>
                        <option value="Dr"> Dr </option>        
                    </select> <br>

                    <table cellspacing="0" border="0" cellpadding="8"
                           style="background-color:#ffcc66;">
                        <tr><td colspan="2" style="padding-bottom:1px;">
                                <a href="http://www.snaphost.com/captcha/ReadyForms/SignUpForm.aspx" alt="form registration" title="form registration" >form registration</a></td></tr>
                        <tr valign="bottom"><td>
                                <a href="#" onclick="return ReloadCaptchaImage('CaptchaImage');"><span style="font-size:12px;">reload image</span></a><br />
                                <a href="http://www.snaphost.com/captcha"><img id="CaptchaImage"
                                                                               alt="captcha program" style="border-width:0px;"
                                                                               title="captcha program"
                                                                               src="https://www.SnapHost.com/captcha/CaptchaImage.aspx?id=36MPYKALZ6T5" /></a>
                            </td><td>  <br /><i>Enter Captcha code</i><br />
                                <input id="skip_CaptchaCode" name="skip_CaptchaCode" type="text"
                                       style="width:130px; height:48px; font-size:38px;" maxlength="6" /><br />
                            </td></tr></table>
                    <script type="text/javascript">
                        function ReloadCaptchaImage(captchaImageId) {
                            var obj = document.getElementById(captchaImageId);
                            var src = '' + obj.src;
                            obj.src = '';
                            var date = new Date();
                            var pos = src.indexOf('&rad=');
                            if (pos >= 0) {
                                src = src.substr(0, pos);
                            }
                            obj.src = src + '&rad=' + date.getTime();
                            return false;
                        }
                    </script>
                    <input id="submit" type="submit" value="Sign up As A StaffMember">



                </div>

            </form>
            <script>
                document.getElementById("my_captcha_form").addEventListener("submit", function (evt)
                {

                    var response = grecaptcha.getResponse();
                    if (response.length == 0)
                    {
                        //reCaptcha not verified
                        alert("please verify you are humann!");
                        evt.preventDefault();
                        return false;
                    }
                    //captcha verified
                    //do the rest of your validations here

                });
            </script>

        </div>  
        <script src="javascriptValidation.js"></script>

    </body>

</html>
