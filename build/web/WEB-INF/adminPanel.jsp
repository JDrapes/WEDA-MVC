<%-- 
    Document   : adminPanel
    Created on : 11-Nov-2019, 15:35:43
    Author     : jordandraper
--%>


<!DOCTYPE HTML>
<html>
    <head>
        <meta charset="UTF-8">
        <title>West England Drivers Association</title>
        <link href="css/fixed-two-column.css" rel="stylesheet">
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <jsp:include page="/WEB-INF/navigationBar.jsp"/> 
            </div>
            <div id="main">
                <div id="menu">
                    <jsp:include page="/WEB-INF/adminSidePanel.jsp"/> 
                </div>
                <div id="content">
                    <h2>Welcome to your profile page</h2>
                    <fieldset>
                        <legend>Editable:</legend>
                        <form method="POST" action="AdminService.do">
                            Email: <input type="text" name="username" value="<%=(String) (request.getAttribute("username"))%>">
                            Full name: <input type="text" name="fullname" value="<%=(String) (request.getAttribute("fullname"))%>">
                            Date of Birth: <input type="text" name="dateofbirth" value="<%=(String) (request.getAttribute("dateofbirth"))%>">
                            Address: <input type="text" name="address" value="<%=(String) (request.getAttribute("address"))%>">
                            </fieldset>

                            <fieldset>
                                <legend>Non-Editable information:</legend>
                                Date of Registration: <input type="text" name="registrationDate" value="<%=(String) (request.getAttribute("dateofregistration"))%>"readonly>
                                Profile Type: <input type="text" name="profileType" value="<%=(String) (request.getAttribute("profiletype"))%>"readonly>
                                Balance: <input type="text" name="accountBalance" value="<%=(String) (request.getAttribute("balance"))%>"readonly>
                            </fieldset>
                            <input name="tbl" type="submit" id="signup" class="form-submit" value="Update profile details"/>
                        </form>
                </div>
                <div class="clearer"></div>
            </div>
        </div>
    </body>
</html>

