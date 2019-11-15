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
    </head>
    <body>
        <main class="pageContainer">
            <div class="row">
                <div class="sidebar text-center">
                    <img src="https://s3.amazonaws.com/uifaces/faces/twitter/mantia/128.jpg" class="img-circle">
                    <h3>Admin Name</h3>
                    <!-- buttons below -->
                    <form method="POST" action="AdminService.do">
                        <input name="tbl" type="submit" class="btn btn-secondary" value="List Users"/>
                    </form>
                    <form method="POST" action="">
                        <input name="button" type="submit" class="btn btn-secondary" value="List all oustanding balances"/>
                    </form>
                    <form method="POST" action="">
                        <input name="button" type="submit" class="btn btn-secondary" value="List all claims"/>
                    </form>
                    <form method="POST" action="">
                        <input name="button" type="submit" class="btn btn-secondary" value="List all professional member applications"/>
                    </form>
                    <form method="POST" action="">
                        <input name="button" type="submit" class="btn btn-secondary" value="Process individual claims"/>
                    </form>
                    <form method="POST" action="">
                        <input name="button" type="submit" class="btn btn-secondary" value="Process membership applications & upgrade if payment was made"/>
                    </form>
                    <form method="POST" action="">
                        <input name="button" type="submit" class="btn btn-secondary" value="Suspend/Resume memberships"/>
                    </form>
                    <form method="POST" action="">
                        <input name="button" type="submit" class="btn btn-secondary" value="Annual turnover inc total income and total pay-outs"/>
                    </form>
                    <form method="POST" action="UserService.do">
                        <input name="tbl" type="submit" class="btn btn-secondary" value="Create a new user"/>
                    </form>
                    <form method="POST" action="UserService.do">
                        <input name="tbl" type="submit" class="btn btn-secondary" value="Delete a User"/>
                    </form>
                    <form method="POST" action="UserService.do">
                        <input name="tbl" type="submit" class="btn btn-secondary" value="Change my password"/>
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
