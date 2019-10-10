<%-- 
    Document   : erro
    Created on : 29/09/2019, 21:30:19
    Author     : lucas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Erro</title>
    </head>
    <body class="text-center">
        <%
            String pg = (String)request.getAttribute("page");
            String msg = (String)request.getAttribute("msg");
        %>
        
        <jsp:useBean id="configuracao" class="bean.ConfigBean" scope="application" />
        
        <br/><br/><br/>
        <div class="container">
            <h1 class="text-danger"><%out.println(msg);%></h1>
            <a href="<%out.println(pg);%>">Retornar</a>
            <footer class="footer fixed-bottom">
                Em caso de problemas, contactar o administrador: <jsp:getProperty name="configuracao" property="mail" />
            </footer>
        </div>
    </body>
</html>
