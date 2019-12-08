<!DOCTYPE HTML>
<html>
    <head>
        <meta charset="UTF-8">
        <title>West England Drivers Association</title>
        <link href="css/fixed-two-column.css" rel="stylesheet">
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <jsp:include page="/WEB-INF/navigationBar.jsp"/> 
            </div>
            <div id="main">
                <div id="menu">
                    <jsp:include page="/WEB-INF/adminSidePanel.jsp"/> 
                </div>
                <div id="content">
                    <h2>Lookup user profiles</h2>
                    <form action="AdminService.do" method="POST">
                        <div>
                            Enter an email to begin: <input type="text" name="lookupemail" value="">
                            <input name="tbl" type="submit" id="claimidbutton" class="form-submit" value="Show user information"/>
                        </div>

                        <div>
                            Email: <input type="text" name="lookupemail" value="<%=(String) (request.getAttribute("lookupemail"))%>"readonly>                           
                            Full name: <input type="text" name="lookupfullname" value="<%=(String) (request.getAttribute("lookupfullname"))%>"readonly>
                            Profile Type: <input type="text" name="lookupprofiletype" value="<%=(String) (request.getAttribute("lookupprofiletype"))%>"readonly>
                            Date of Birth: <input type="text" name="lookupdob" value="<%=(String) (request.getAttribute("lookupdob"))%>"readonly>
                            Date of Registration: <input type="text" name="lookupdateofregistration" value="<%=(String) (request.getAttribute("lookupdateofregistration"))%>"readonly>
                            Outstanding Balance: <input type="text" name="lookupoutstandingbalance" value="<%=(String) (request.getAttribute("lookupoutstandingbalance"))%>"readonly>                            
                            Balance: <input type="text" name="lookupbalanace" value="<%=(String) (request.getAttribute("lookupbalance"))%>"readonly>
                            Address: <input type="text" name="lookupaddress" value="<%=(String) (request.getAttribute("lookupaddress"))%>"readonly>
                        </div>                          
                    </form>
                </div>
                <div class="clearer"></div>
            </div>
        </div>
    </body>
</html>
