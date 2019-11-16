<%-- 
    Document   : navigationBar
    Created on : 11-Nov-2019, 16:01:48
    Author     : jordandraper
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="css/customChanges.css">
<!DOCTYPE html>

<!-- Will need to at a str and url for logging out if the user session is active... -->  

<!-- NAVBAR -->
<nav class="navbar navbar-dark bg-dark">

    <a class="navbar-brand" href="index.jsp">West England Driver Association</a>

    <div class="navbar-nav">      
        <form method="POST" action="AdminService.do">
            <input name="tbl" type="submit" class="btn btn-outline-secondary" value="Sign in"/>
        </form>
    </div>

</nav>
<!-- END OF NAVBAR -->

<!-- JS -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
