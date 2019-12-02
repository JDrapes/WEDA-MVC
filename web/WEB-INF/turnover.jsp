<%-- 
    Document   : turnover
    Created on : 22-Nov-2019, 15:47:43
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
                    <h2>Annual Turnover</h2>  
                    <p>Net turnover - <%=(String) (request.getAttribute("netturnover"))%></p>
                    <p>Yearly income - <%=(String) (request.getAttribute("incometransactions"))%></p>
                    <p>Yearly outgoings - <%=(String) (request.getAttribute("outgoingtransactions"))%></p>
                    
                    
                    
                    <div>
                        <h2>All transactions</h2>
                    <%=(String) (request.getAttribute("alltransactions"))%>

                    </div>
           
                    
                </div>
                <div class="clearer"></div>
            </div>
        </div>
    </body>
</html>