<%-- 
    Document   : processIndividualClaims
    Created on : 20-Nov-2019, 21:08:14
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
                    <h2>Process individual claims</h2>
                    <form action="AdminService.do" method="POST">
                        <div>
                            Enter a claim ID to begin: <input type="text" name="claimid" value="">
                            <input name="tbl" type="submit" id="claimidbutton" class="form-submit" value="Show claim information"/>
                        </div>

                        <div>
                            Claim ID <input type="text" name="claimnumber" value="<%=(String) (request.getAttribute("claimnumber"))%>"readonly>
                            Claimant name: <input type="text" name="claimusername" value="<%=(String) (request.getAttribute("claimusername"))%>"readonly>
                            Claim date: <input type="text" name="claimdate" value="<%=(String) (request.getAttribute("claimdate"))%>"readonly>
                            Claim amount <input type="text" name="claimamount" value="<%=(String) (request.getAttribute("claimamount"))%>"readonly>
                            Claim status: <input type="text" name="claimstatus" value="<%=(String) (request.getAttribute("claimstatus"))%>"readonly>
                            Claim description: <input type="text" name="claimdescription" value="<%=(String) (request.getAttribute("claimdescription"))%>"readonly>

                        </div>

                        <input name="tbl" type="submit" id="signup" class="form-submit" value="Payout claim"/>
                        <input name="tbl" type="submit" id="signup" class="form-submit" value="Deny claim"/>  
                    </form>

                    <p><%=(String) (request.getAttribute("message"))%></p>
                </div>
                <div class="clearer"></div>
            </div>
        </div>
    </body>
</html>
