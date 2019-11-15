<%-- 
    Document   : navigationBar
    Created on : 11-Nov-2019, 16:01:48
    Author     : jordandraper
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%! int i = 0;
    String str = "Sign in";
    String url = "Signin.do";
%>

<!-- NAVBAR -->
<nav class="navbar navbar-dark bg-dark">
    <a class="navbar-brand" href="index.jsp">West England Driver Association</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="index.jsp">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Panel (admin panel/user panel <span class="sr-only">(current)</span></a>
            </li>                
            <li class="nav-item">
                <form method="POST" action="AdminService.do">
                    <input name="tbl" type="submit" class="signup-image-link" value="Sign in"/>
                    <a name="tbl" class="nav-link" value="Sign in">Login / Register</a>

                </form>

            </li>
        </ul>
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
