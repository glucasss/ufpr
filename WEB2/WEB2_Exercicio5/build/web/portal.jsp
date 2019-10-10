<%-- 
    Document   : portal
    Created on : 29/09/2019, 21:14:28
    Author     : lucas
--%>

<%@page import="bean.LoginBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <title>PortalServlet</title>
    </head>
    <body class="text-center">
        <div class="container">
        
            <%

                LoginBean userSession = (LoginBean) session.getAttribute("loginBean");

                if (userSession == null) {  
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
                    request.setAttribute("msg", "Usuário deve se autenticar para usar o sistema.");
                    rd.forward(request, response);
                    session.invalidate();
                }

            %>

            <br/><br/><br/>
            
            <jsp:useBean id="loginBean" class="bean.LoginBean" scope="session" />
            <jsp:useBean id="configuracao" class="bean.ConfigBean" scope="application" />

            <h1>Usuário logado: <jsp:getProperty name="loginBean" property="loginUsuario" /></h1>
            <br/>
            <a href="ClientesServlet">Cadastro de Clientes</a>
            <br/>
            <a href="LogoutServlet">Sair</a>
            
            
        
            <footer class="footer fixed-bottom">
                Em caso de problemas, contactar o administrador: <jsp:getProperty name="configuracao" property="mail" />
            </footer>
        </div>
    </body>
</html>
