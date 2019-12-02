

<!DOCTYPE HTML>
<html>
    <head>
        <meta charset="UTF-8">
        <title>West England Drivers Association</title>
        <link href="css/fixed-two-column.css" rel="stylesheet">
        
        <!-- Main css -->
        <link rel="stylesheet" href="css/style.css">
        <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="css/customChanges.css">    
        
                <!-- Custom styles -->
        <link href="css/freelancer.min.css" rel="stylesheet">
        <link href="css/freelancer.css" rel="stylesheet">
        <!-- Custom fonts  -->
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css">

    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <jsp:include page="/WEB-INF/navigationBar.jsp"/> 
            </div>
            <div id="main">
                <div id="menu">
                    
                    
                </div>
                <div id="content">
                    <h2>Please provide your following details</h2>
                    <form method="POST" action="Update.do">     
                        <table>

                            <tr>
                                <th></th>
                            </tr>
                            <tr>
                                <td>Username:</td>
                                <td><input type="text" name="username"/></td>
                            </tr>
                            <tr>
                                <td>New Password:</td>
                                <td><input type="password" name="password"/></td>
                            </tr>
                            <tr>
                                <td>Confirm Password:</td>
                                <td><input type="password" name="newpasswd"/></td>
                            </tr>
                            <tr> 
                                <td> <input type="submit" value="Change"/></td>
                            </tr>
                        </table>
                    </form>
                    <%=((String) (request.getAttribute("msg")) != null) ? (String) (request.getAttribute("msg")) : ""%>
                    <jsp:include page="/WEB-INF/foot.jsp"/>
                </div>
                <div class="clearer"></div>
            </div>
        </div>
    </body>
</html>





