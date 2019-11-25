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
                    <h2>Submit a claim</h2>
                    <p>Please enter your claim amount and a description of why you are claiming</p>
                    <form method="POST" action="AdminService.do">
                        Claim amount: <input type="text" name="claimamount" value="<%=(String) (request.getAttribute("claimamount"))%>">
                        Claim description: <input type="text" name="claimdescription" value="<%=(String) (request.getAttribute("claimdescription"))%>">
                        <input name="tbl" type="submit" id="submitaclaim" class="form-submit" value="Submit my claim"/>
                    </form>
                        <%=(String) (request.getAttribute("responseMessage"))%>
                </div>

                <div class="clearer"></div>
            </div>
        </div>
    </body>
</html>