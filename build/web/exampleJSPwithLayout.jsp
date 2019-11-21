<!DOCTYPE HTML>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Two-column fixed layout</title>
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
                    <h2>Content</h2>
                    <p>This column is fixed.</p>
                </div>
                <div class="clearer"></div>
            </div>
        </div>
    </body>
</html>