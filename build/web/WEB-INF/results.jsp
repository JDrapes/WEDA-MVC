<%-- 
    Document   : results
    Created on : 30-Oct-2015, 10:02:53
    Author     : me-aydin
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
                        <h1>Listing all members</h1>

                        <%=(String) (request.getAttribute("query"))%>


                        <jsp:include page="/WEB-INF/foot.jsp"/>
                </td>

            </tr>
        </div>
    </table>

</body>
</html>
