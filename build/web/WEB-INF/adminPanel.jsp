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
                            Full name: <input type="text" name="name" value="">
                            Date of Birth: <input type="text" name="dob" value="">
                            Address: <input type="text" name="address" value="">
                        </fieldset>

                            
                        <fieldset>
                            <legend>Non-Editable information:</legend>
                            Date of Registration: <input type="text" name="registrationDate" value="">
                            Profile Type <input type="text" name="profileType" value="">
                            Balance <input type="text" name="accountBalance" value="">
                        </fieldset>

                </td>

            </tr>
        </div>
    </table>

</body>
</html>

