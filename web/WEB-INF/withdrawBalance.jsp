<%-- 
    Document   : withdrawBalance
    Created on : 27-Nov-2019, 22:43:41
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
                    <jsp:include page="/WEB-INF/customerSidePanel.jsp"/> 
                </div>
                <div id="content">
                    <h2>Below you can withdraw balance to your PayPal</h2>
                     Balance available to withdraw: <input type="text" name="accountBalance" value="<%=(String) (request.getAttribute("balance"))%>"readonly>

                    
                    <form method="POST" action="AdminService.do">
                        PayPal: <input type="text" name="paypal" value="">
                        Withdraw amount: <input type="text" name="withdrawamount" value="">
                        <input name="tbl" type="submit" id="submitaclaim" class="form-submit" value="Withdraw my cash"/>
                    </form>
                        <%=(String) (request.getAttribute("responseMessage"))%>
                </div>
                <div class="clearer"></div>
            </div>
        </div>
    </body>
</html>