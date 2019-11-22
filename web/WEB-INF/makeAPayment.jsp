<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    </head>
    <body>
        <jsp:include page="/WEB-INF/navigationBar.jsp"/> 

        <table float="left" height="100%" cellpadding="0">
            <tr>
                <td>
                    <jsp:include page="/WEB-INF/customerSidePanel.jsp"/> 
                </td>
                <td float="top">
                    <div class="content">
                        <h1>Make a Payment</h1>
                        <p>Please make sure to pay your outstanding balances on time otherwise your account may be deactivated!</p>

                        Outstanding Balance <input type="text" name="outstandingbalance" value="<%=(String) (request.getAttribute("outstandingbalance"))%>"readonly>
                        Credit Card Number <input type="text" name="creditcardnumber" value="">
                        Expiry Date <input type="text" name="expirydate" value="">
                        Billing Address <input type="text" name="address" value="<%=(String) (request.getAttribute("address"))%>">

                        <form action="AdminService.do" method="POST">
                            <input type="text" name="amountToPay" id="amountToPay" placeholder="You are paying: "/>
                            <input name="tbl" type="submit" id="signup" class="form-submit" value="Pay now"/>
                        </form>
                </td>

            </tr>
        </div>
    </table> 

</body>
</html>
