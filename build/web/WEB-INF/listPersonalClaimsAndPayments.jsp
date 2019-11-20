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
                        <h1>Below is all your claims and payments to date</h1>
                        <p>Your payments to us will have the description of "Payment to WEDA"</p>
                        
                        <!-- Grainger - you need to add the table in here, check JDBC there is some stuff for rs to table and database querying. -->
                </td>

            </tr>
        </div>
    </table> 

</body>
</html>
