<%-- 
    Document   : clientesListar
    Created on : 02/10/2019, 19:43:49
    Author     : lucas
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList"%>
<%@page import="bean.Cliente"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="bean.LoginBean"%>
<%@page errorPage="erro.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%

    LoginBean userSession = (LoginBean) session.getAttribute("loginBean");

    if (userSession == null) {  
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
        request.setAttribute("msg", "Usuário deve se autenticar para usar o sistema.");
        rd.forward(request, response);
        session.invalidate();
    }

%>

<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de Clientes</title>
    </head>
    <body>
        <div class="container">
            <br/>
            <h1 class="text-center">Clientes</h1>
            <hr/>
            <table class="table table-bordered w-75" style="margin: 0 auto !important;">
                <thead>
                    <tr>
                        <th style="width:15%">CPF</th>
                        <th>Nome</th>
                        <th style="width:27%">Email</th>
                        <th style="width:13%">Ações</th>
                    </tr>
                </thead>
                <tbody>
                    
                    <c:forEach items="${clientes}" var="c">
                        <tr>
                            <td>${c.cpfCliente}</td>
                            <td>${c.nomeCliente}</td>
                            <td>${c.emailCliente}</td>
                            <td>
                                <a href="ClientesServlet?action=show&id=${c.idCliente}"><i class="fas fa-search-plus"></i></a> |
                                <a href="ClientesServlet?action=formUpdate&id=${c.idCliente}"><i class="fas fa-edit"></i></a> |
                                <a href="ClientesServlet?action=remove&id=${c.idCliente}"><i class="fas fa-trash-alt"></i></a>
                            </td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="4"><button onclick="location.href='ClientesServlet?action=formNew'" type="button" class="btn btn-success">Novo</button></td>
                    </tr>
                </tbody>
            </table>
        </div>
    </body>
</html>
