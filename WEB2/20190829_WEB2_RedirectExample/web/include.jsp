<%-- 
    Document   : include
    Created on : 29/08/2019, 19:52:49
    Author     : lucas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Include</title>
    </head>
    <body>
        <h2>Isto foi incluído</h2>
        <%
            for (int i=0; i<10; i++) {
                out.println(i + " - ");
            }
        %>
    </body>
</html>