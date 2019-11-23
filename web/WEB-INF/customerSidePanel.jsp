<%-- 
    Document   : customerSidePanel
    Created on : 15-Nov-2019, 17:17:35
    Author     : jordandraper
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>West England Drivers Association</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- Main css -->
        <link rel="stylesheet" href="css/style.css">
        <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="css/customChanges.css">
    </head>
    <body>
        <main class="pageContainer">
            <div class="row">
                <div style="height:100vh" class="sidebar text-center">
                    <img src="https://s3.amazonaws.com/uifaces/faces/twitter/mantia/128.jpg" class="img-circle">
                    <h3><h3><%=(String) (request.getAttribute("username"))%></h3></h3>
                    <!-- buttons below -->
                    <form method="POST" action="AdminService.do">
                        <input name="tbl" type="submit" class="btn btn-secondary" value="Customer profile page"/>
                        <input name="tbl" type="submit" class="btn btn-secondary" value="Check outstanding balance"/>
                        <input name="tbl" type="submit" class="btn btn-secondary" value="List all payments and claims to date"/>
                        <input name="tbl" type="submit" class="btn btn-secondary" value="Make a payment"/>
                        <input name="tbl" type="submit" class="btn btn-secondary" value="Submit a claim"/>
                        <!-- Change your password  --> 
                        <input name="tbl" type="submit" class="btn btn-secondary" value="Change my password"/>
                    </form>


                    <!-- end of buttons -->
                </div>
                <div class="content">

                </div>
            </div>
        </main>
    </body>
</html>
