<%-- 
    Document   : navigationBar
    Created on : 11-Nov-2019, 16:01:48
    Author     : jordandraper
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="css/customChanges.css">
<!DOCTYPE html>

<%! int i = 0;
    String str = "Sign in";
    String url = "Signin.do";
%>
<!-- Will need to at a str and url for logging out if the user session is active... -->  


<!-- NAVBAR -->
<nav class="navbar navbar-dark bg-dark">

    <a class="navbar-brand" href="index.jsp">West England Driver Association</a>

    <div class="navbar-nav">      
        <form method="POST" action="<%=url%>">
            <input name="tbl" type="submit" class="btn btn-outline-secondary" value="<%=str%>"/>
        </form>
    </div>

</nav>
<!-- END OF NAVBAR -->

<%
    if (i++ > 0 && request.getAttribute("message") != null) {
        out.println(request.getAttribute("message"));
        i--;
    }
%>

<!-- JS -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
