

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign In</title>
  <link rel="stylesheet"  type="text/css" href="style.css" >
    </head>
    <body>
                
        <div class="container">

            <form id="signup" action="<%=request.getContextPath()%>/signIn" method="GET">

        <div class="header">
        
            <h3>Sign In</h3>

        </div>
        
        <div class="sep"></div>

        <div class="inputs">
            
 
            <input type="email" placeholder="e-mail"  name="email" required/>
            <input type="password" placeholder="Password"name="password" required />   

            <input id="submit" type="submit" value="Sign In">
            

        
        </div>

    </form>


</div>
        
    </body>
</html>
