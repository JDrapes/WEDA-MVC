<%-- 
    Document   : adminSidePanel
    Created on : 15-Nov-2019, 17:01:00
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
        <main class="pageContainer">
            <div class="row">
                <div class="sidebar text-center">
                    <img src="https://images.discordapp.net/attachments/186582898613288960/327207210277011457/rick_by_ghuzz_buzz-d9qs3nr.png" class="img-circle">
                    <h3><%=(String) (request.getAttribute("username"))%></h3>
                    <!-- buttons below -->
                    <form method="POST" action="AdminService.do">
                        <input name="tbl" type="submit" class="btn btn-secondary" value="Admin profile page"/>
                        <!-- List all users --> 
                        <input name="tbl" type="submit" class="btn btn-secondary" value="List Users"/>
                        <!-- List all outstanding balances --> 
                        <input name="tbl" type="submit" class="btn btn-secondary" value="List all oustanding balances"/>
                        <!-- List all claims --> 
                        <input name="tbl" type="submit" class="btn btn-secondary" value="List all claims"/>
                        <!-- List all professional membership applications with option to upgrade them, also suspend/resume memberships -->
                        <input name="tbl" type="submit" class="btn btn-secondary" value="Manage Memberships"/> 
                        <!-- Process individual claims --> 
                        <input name="tbl" type="submit" class="btn btn-secondary" value="Process individual claims"/>
                        <!-- Annual turnover including total income and total pay-outs --> 
                        <input name="tbl" type="submit" class="btn btn-secondary" value="Annual turnover - inc and out"/>
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
