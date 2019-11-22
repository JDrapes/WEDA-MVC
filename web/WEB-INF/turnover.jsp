<%-- 
    Document   : turnover
    Created on : 22-Nov-2019, 15:47:43
    Author     : jordandraper
--%>
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
                    <h2>Annual Turnover</h2>

                    <div>
                    <h2>Net Income</h2>  
                    <p>Income here</p>
                    </div>
                    
                    <div>
                    <h2>Income</h2>
                    <p>Info here</p>
                    </div>
                    
                    <div>
                    <h2>Pay-outs</h2>
                    <p>Info here</p>
                    </div>
                </div>
                <div class="clearer"></div>
            </div>
        </div>
    </body>
</html>