<%-- 
    Document   : index
    Created on : 02/10/2019, 19:03:36
    Author     : lucas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page errorPage="erro.jsp"%>
<!DOCTYPE html>

<%
    String msg = (String)request.getAttribute("msg");
%>
<jsp:useBean id="configuracao" class="bean.ConfigBean" scope="application" />

<html>
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/login.css">
        <title>Login</title>
    </head>
    <body class="text-center">
        <form class="form-signin" method="post" action="LoginServlet">
            <h1 class="h3 mb-3 font-weight-normal">Entre com seus dados</h1>
            <label for="inputEmail" class="sr-only">Login</label>
            <input type="text" id = "inputEmail" name="inputEmail" class="form-control" placeholder="Login" required autofocus>
            <label for="inputPassword" class="sr-only">Senha</label>
            <input type="password" id="inputPassword" name="inputPassword" class="form-control" placeholder="Senha" required>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Entrar</button>
            <p class="mt-5 mb-3 text-muted">&copy; 2019</p>
            <%if(msg != null){
                out.println("<p class=\"text-danger\">"+msg+"</p>");
            }%>
        </form>
        <footer class="footer fixed-bottom">
            Em caso de problemas, contactar o administrador: <jsp:getProperty name="configuracao" property="mail" />
        </footer>
    </body>
</html>
