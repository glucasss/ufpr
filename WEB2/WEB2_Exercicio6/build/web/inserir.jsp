<%-- 
    Document   : inserir
    Created on : 29/09/2019, 22:30:36
    Author     : lucas
--%>

<%@page import="bean.LoginBean"%>
<%@page errorPage="erro.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inserir</title>
    </head>
    <body>
        <%
            LoginBean userSession = (LoginBean) session.getAttribute("loginBean");

            if (userSession == null) {  
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/erro.jsp");
                request.setAttribute("msg", "Não foi possível encontrar o usuário.\nTente novamente.");
                request.setAttribute("page", "index.html");
                rd.forward(request, response);
                session.invalidate();
            }
        %>
        <br/><br/>
        <h1 class="text-center">Cadastrar novo usuário</h1>
        <br/>
        <div class="container w-25">
            <form action="CadastrarUsuarioServlet" method="post">
                <div class="form-group">
                    <label for="inputNome">Nome</label>
                    <input type="text" class="form-control" id="inputNome" name="nome" placeholder="ex: João da Silva"/>
                </div>
                <div class="form-group">
                    <label for="inputLogin">Login</label>
                    <input type="text" class="form-control" id="inputLogin" name="login" placeholder="ex: joaosilva"/>
                </div>
                <div class="form-group">
                    <label for="inputSenha">Senha</label>
                    <input type="password" class="form-control" id="inputSenha" name="senha"/>
                </div>
                <button class="btn btn-lg btn-primary btn-block" type="submit">Salvar</button>
            </form>
    </body>
</html>
