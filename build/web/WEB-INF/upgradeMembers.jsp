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
                        <h1>Listing all members: use the form below to upgrade members</h1>
                        <p>Members must pay to upgrade their accounts!</p>

                        <%=(String) (request.getAttribute("query"))%>                 

                        <form action="AdminService.do" method="POST">
                            <input type="text" name="userToUpgrade" id="userToUpgrade" placeholder="Enter a username to upgrade"/>
                            <input name="tbl" type="submit" name="UpgradeProvisionalToMember" id="signup" class="form-submit" value="Upgrade provisional member"/>
                            <input name="tbl" type="submit" name="UpgradeProvisionalToMember" id="signup" class="form-submit" value="Suspend membership"/>
                            <input name="tbl" type="submit" name="UpgradeProvisionalToMember" id="signup" class="form-submit" value="Resume membership"/>
                        </form>




                        <jsp:include page="/WEB-INF/foot.jsp"/>
                </td>

            </tr>
        </div>
    </table> 

</body>
</html>
