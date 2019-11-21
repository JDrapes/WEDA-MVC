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
                    <jsp:include page="/WEB-INF/customerSidePanel.jsp"/> 
                </div>
                <div id="content">
                    <h2>These are all your claims</h2>
                    <p>This column is fixed.</p>
                    <%=(String) (request.getAttribute("query"))%>
                </div>
                <div class="clearer"></div>
            </div>
        </div>
    </body>
</html>