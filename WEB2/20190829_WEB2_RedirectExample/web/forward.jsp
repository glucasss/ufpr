<%-- 
    Document   : forward
    Created on : 29/08/2019, 19:53:57
    Author     : lucas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Forward</title>
    </head>
    <body>
        <h2>Foi REDIRECIONADO</h2>
        <%
            for (int i=9; i>=0; i--) {
                out.println(i + " - ");
            }
        %>
    </body>
</html>
