<%-- 
    Document   : adminPanel
    Created on : 11-Nov-2019, 15:35:43
    Author     : jordandraper
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>West England Drivers Association</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- Main css -->
        <link rel="stylesheet" href="/css/style.css">
        <link href="/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="/css/customChanges.css">
    </head>
    <body>

        <jsp:include page="/WEB-INF/navigationBar.jsp"/> 

        <table float="top" height="100%" cellpadding="0">
            <tr>
                <td>
                    <jsp:include page="/WEB-INF/adminSidePanel.jsp"/> 
                </td>
                <td float="left">
                    
                    <div class="contentsOfPage">
                        <h1>Welcome to your profile page</h1>
                        <fieldset>
                            <legend>Editable:</legend>
                            Email: <input type="text" name="email" value="<%=(String) (request.getAttribute("username"))%>">
                            Full name: <input type="text" name="name" value="<%=(String) (request.getAttribute("fullname"))%>">
                            Date of Birth: <input type="text" name="dob" value="<%=(String) (request.getAttribute("dateofbirth"))%>">
                            Address: <input type="text" name="address" value="<%=(String) (request.getAttribute("address"))%>">
                        </fieldset>

                            
                        <fieldset>
                            <legend>Non-Editable information:</legend>
                            Date of Registration: <input type="text" name="registrationDate" value="<%=(String) (request.getAttribute("dateofregistration"))%>">
                            Profile Type <input type="text" name="profileType" value="<%=(String) (request.getAttribute("profiletype"))%>">
                            Balance <input type="text" name="accountBalance" value="<%=(String) (request.getAttribute("balance"))%>">
                        </fieldset>

                </td>

            </tr>
        </div>
    </table>

</body>
</html>

