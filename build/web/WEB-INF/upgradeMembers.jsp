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
                    <h2>Listing all members: use the form below to upgrade members</h2>
                    <p>Members must pay to upgrade their accounts!</p>

                    <%=(String) (request.getAttribute("query"))%>    

                    <form action="AdminService.do" method="POST">
                        <input type="text" name="userToUpgrade" id="userToUpgrade" placeholder="Enter a username and press a button"/>
                        <input name="tbl" type="submit" id="signup" class="form-submit" value="Upgrade provisional member"/>
                        <input name="tbl" type="submit" id="signup" class="form-submit" value="Suspend membership"/>
                        <input name="tbl" type="submit" id="signup" class="form-submit" value="Resume membership"/>
                        <input name="tbl" type="submit" id="signup" class="form-submit" value="Delete a user"/>

                    </form>
                </div>
                <div class="clearer"></div>
            </div>
        </div>
    </body>
</html>
