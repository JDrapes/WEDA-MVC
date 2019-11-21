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
                    <h2>Make a Payment</h2>
                    <p>Please make sure to pay your outstanding balances on time otherwise your account may be deactivated!</p>

                    Outstanding Balance <input type="text" name="outstandingbalance" value="<%=(String) (request.getAttribute("outstandingbalance"))%>">
                    Credit Card Number <input type="text" name="creditcardnumber" value="">
                    Expiry Date <input type="text" name="expirydate" value="">
                    Billing Address <input type="text" name="address" value="<%=(String) (request.getAttribute("address"))%>">

                    <form action="AdminService.do" method="POST">
                        <input type="text" name="amountToPay" id="amountToPay" placeholder="You are paying: "/>
                        <input name="tbl" type="submit" id="signup" class="form-submit" value="Pay now"/>
                    </form>


                </div>
                <div class="clearer"></div>
            </div>
        </div>
    </body>
</html>


