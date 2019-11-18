<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    </head>
    <body>
        <%! int i = 0;
            String str = "Upgrade provisional member";
            String url = "UpgradeProvisionalToMember.do";
        %>
        <jsp:include page="/WEB-INF/navigationBar.jsp"/> 

        <table float="left" height="100%" cellpadding="0">
            <tr>
                <td>
                    <jsp:include page="/WEB-INF/adminSidePanel.jsp"/> 
                </td>
                <td float="top">
                    <div class="content">
                        <h1>Listing all members: use the form below to upgrade members</h1>

                        <%=(String) (request.getAttribute("query"))%>



                        <form action="<%=url%>" method="POST">
                            <input type="text" name="userToUpgrade" id="userToUpgrade" placeholder="Enter a username to upgrade"/>
                            <input type="submit" name="UpgradeProvisionalToMember" id="signup" class="form-submit" value="<%=str%>"/>
                        </form>
                        
                        <jsp:include page="/WEB-INF/foot.jsp"/>
                </td>

            </tr>
        </div>
    </table>
<%
                                    if (i++ > 0 && request.getAttribute("message") != null) {
                                        out.println(request.getAttribute("message"));
                                        i--;
                                    }
                                %>
</body>
</html>
