<%-- 
    Document   : processIndividualClaims
    Created on : 20-Nov-2019, 21:08:14
    Author     : jordandraper
--%>

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
                    <jsp:include page="/WEB-INF/adminSidePanel.jsp"/> 
                </td>
                <td float="top">
                    <div class="content">
                        <h1>Process individual claims</h1>
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


                </td>

            </tr>
        </div>
    </table> 

</body>
</html>

