<%-- 
    Document   : register
    Created on : 11-Nov-2019, 15:34:12
    Author     : jordandraper
--%>
<%@page import="model.Jdbc"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>West England Drivers Association</title>

        <!-- Font Icon -->
        <link rel="stylesheet" href="fonts/material-icon/css/material-design-iconic-font.min.css">

        <!-- Main css -->
        <link rel="stylesheet" href="css/style.css">
        <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    </head>
    <body>
        <%! int i = 0;
            String str = "Register";
            String url = "NewUser.do";
        %>
        <%
            if ((String) request.getAttribute("msg") == "del") {
                str = "Delete";
                url = "Delete.do";
            } else {
                str = "Register";
                url = "NewUser.do";
            }
        %>

        <jsp:include page="/WEB-INF/navigationBar.jsp"/>

        <div class="main">

            <!-- Sign up form -->
            <section class="signup">
                <div class="container">
                    <div class="signup-content">
                        <div class="signup-form">
                            <h2 class="form-title">Register</h2>
                            <form action="<%=url%>" method="POST" class="register-form" id="register-form">
                                <div class="form-group">
                                    <label for="username"><i class="zmdi zmdi-account material-icons-name"></i></label>
                                    <input type="text" name="username" id="name" placeholder="Your email"/>
                                </div>
                                <div class="form-group">
                                    <label for="password"><i class="zmdi zmdi-email"></i></label>
                                    <input type="password" name="password" id="email" placeholder="enter a password"/>
                                </div>
                                <div class="form-group">
                                    <input type="checkbox" name="agree-term" id="agree-term" class="agree-term" />
                                    <label for="agree-term" class="label-agree-term"><span><span></span></span>I agree all statements in  <a href="#" class="term-service">Terms of service</a></label>
                                </div>
                                <div class="form-group form-button">
                                    <input type="submit" name="signup" id="signup" class="form-submit" value="<%=str%>"/>
                                </div>
                            </form>
                        </div>
                        <div class="signin-image">
                            <figure><img src="img/signup-image.jpg" alt="sign up image"></figure>
                            <form method="POST" action="AdminService.do">
                                <input name="tbl" type="submit" class="signup-image-link" value="Sign in"/>
                                <!-- Need to make sign in -->
                            </form>
                        </div>
                    </div>
                </div>
            </section>
        </div>


        <%
            if (i++ > 0 && request.getAttribute("message") != null) {
                out.println(request.getAttribute("message"));
                i--;
            }
        %>
    </body>
</html>
