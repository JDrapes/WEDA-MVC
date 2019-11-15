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
                <div class="sidebar text-center">
                    <img src="https://s3.amazonaws.com/uifaces/faces/twitter/mantia/128.jpg" class="img-circle">
                    <h3>Customer Name</h3>
                    <!-- buttons below -->
                    <form method="get" action="">
                        <input name="button" type="submit" class="btn btn-secondary" value="Check outstanding balance"/>
                    </form>
                    <form method="get" action="">
                        <input name="button" type="submit" class="btn btn-secondary" value="List all payments and claims to date"/>
                    </form>
                    <form method="get" action="">
                        <input name="button" type="submit" class="btn btn-secondary" value="Make a payment"/>
                    </form>
                    <form method="get" action="">
                        <input name="button" type="submit" class="btn btn-secondary" value="Submit a claim"/>
                    </form>
                  <form method="POST" action="CustomerService.do">
                        <input name="tbl" type="submit" class="btn btn-secondary" value="List Users"/>
                    </form>
                    <!-- end of buttons -->
                </div>
                <div class="content">
                    <h1>Title</h1>
                    

                </div>
            </div>
        </main>
    </body>
</html>
