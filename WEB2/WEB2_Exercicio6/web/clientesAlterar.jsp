<%-- 
    Document   : clientesAlterar
    Created on : 03/10/2019, 16:59:27
    Author     : lucas
--%>

<%@page import="bean.Cliente"%>
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
        <link rel="stylesheet" href="css/formstyle.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cliente - ${cliente.nomeCliente}</title>
    </head>
    <body>
        <div class="container">
            <div class="main">
                <div class="main-center">
                    <br/>
                    <h1 class="text-center">Alterar Cliente</h1>
                    <form action="ClientesServlet?action=update" method="post">
                        <div class="form-group">
                            <label for="nomeCliente">Nome</label>
                            <div class="input-group">
                                <input type="text" class="form-control" id="nomeCliente" name="nomeCliente" value="${cliente.nomeCliente}" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="idCliente">Id</label>
                            <div class="input-group">
                                <input readonly type="text" class="form-control" id="idCliente" name="idCliente" value="${cliente.idCliente}" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="cpfCliente">CPF</label>
                            <div class="input-group">
                                <input type="text" class="form-control" id="cpfCliente" name="cpfCliente" value="${cliente.cpfCliente}" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="emailCliente">Email</label>
                            <div class="input-group">
                                <input type="text" class="form-control" id="emailCliente" name="emailCliente" value="${cliente.emailCliente}" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="dataCliente">Data</label>
                            <div class="input-group">
                                <input type="date" class="form-control" id="dataCliente" name="dataCliente" value="${cliente.dataCliente}" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="ruaCliente">Rua</label>
                            <div class="input-group">
                                <input type="text" class="form-control" id="ruaCliente" name="ruaCliente" value="${cliente.ruaCliente}" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="numCliente">Numero</label>
                            <div class="input-group">
                                <input type="number" class="form-control" id="numCliente" name="numCliente" value="${cliente.numCliente}" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="cepCliente">CEP</label>
                            <div class="input-group">
                                <input type="text" maxlength="8" class="form-control" id="cepCliente" name="cepCliente" value="${cliente.cepCliente}" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="cidadeCliente">Cidade</label>
                            <div class="input-group">
                                <input type="text" class="form-control" id="cidadeCliente" name="cidadeCliente" value="${cliente.cidadeCliente}" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="ufCliente">UF</label>
                            <div class="input-group">
                                <input type="text" maxlength="2" class="form-control" id="ufCliente" name="ufCliente" value="${cliente.ufCliente}" />
                            </div>
                        </div>
                        <br/>
                        <button type="submit" class="btn btn-light">Alterar</button>
                        <button name="voltar" formaction="ClientesServlet?action=list" class="btn btn-danger">Cancelar</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
