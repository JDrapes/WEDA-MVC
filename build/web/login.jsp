<%-- 
    Document   : login
    Created on : 11-Nov-2019, 15:32:57
    Author     : jordandraper
--%>

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
            String str = "Sign in";
            String url = "Signin.do";
        %>

        <jsp:include page="/WEB-INF/navigationBar.jsp"/>

        <div class="main">
            <!-- Sing in  Form -->
            <section class="sign-in">
                <div class="container">
                    <div class="signin-content">
                        <div class="signin-image">
                            <figure><img src="img/signin-image.jpg" alt="login image"></figure>
                            <form method="POST" action="AdminService.do">
                                <input name="tbl" type="submit" class="signup-image-link" value="Create a new user"/>
                            </form>
                        </div>

                        <div class="signin-form">
                            <h2 class="form-title">Login</h2>

                            <form action="<%=url%>" method="POST" class="register-form" id="login-form">
                                <div class="form-group">
                                    <label for="your_email"><i class="zmdi zmdi-account material-icons-name"></i></label>
                                    <input type="text" name="txtemail" id="your_email" placeholder="Email"/>
                                </div>

                                <div class="form-group">
                                    <label for="your_pass"><i class="zmdi zmdi-lock"></i></label>
                                    <input type="password" name="txtpassword" id="your_pass" placeholder="Password"/>
                                </div>

                                <div class="form-group">
                                    <input type="checkbox" name="remember-me" id="remember-me" class="agree-term" />
                                    <label for="remember-me" class="label-agree-term"><span><span></span></span>Remember me</label>
                                </div>      

                                <div class="form-group form-button">
                                    <input type="submit" name="signin" id="signin" class="form-submit" value="<%=str%>"/>
                                </div>                                
                            </form>  
                        </div>
                    </div>
                </div>
            </section>
                              <%
                                    if (i++ > 0 && request.getAttribute("message") != null) {
                                        out.println(request.getAttribute("message"));
                                        i--;
                                    }
                                %>
        </div>
    </body>

</html>
