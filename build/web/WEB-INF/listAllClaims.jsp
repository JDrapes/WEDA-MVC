<%-- 
    Document   : listAllClaims
    Created on : 20-Nov-2019, 21:06:14
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
                        <h1>List of all claims from all users below</h1>
                        <%=(String) (request.getAttribute("query"))%>    


                        
                </td>

            </tr>
        </div>
    </table> 

</body>
</html>
