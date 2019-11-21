<%-- 
    Document   : listAllClaims
    Created on : 20-Nov-2019, 21:06:14
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
                    <h2>List of all claims from all users below</h2>
                    <%=(String) (request.getAttribute("query"))%>
                </div>
                <div class="clearer"></div>
            </div>
        </div>
    </body>
</html>


