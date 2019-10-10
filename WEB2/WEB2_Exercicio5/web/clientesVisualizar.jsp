<%-- 
    Document   : clientesVisualizar
    Created on : 03/10/2019, 20:20:27
    Author     : lucas
--%>

<%@page import="bean.Cliente"%>
<%@page import="bean.LoginBean"%>
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
    
    Cliente cliente = new Cliente();
    cliente = (Cliente)request.getAttribute("cliente");

%>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/formstyle.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cliente - <%out.println(cliente.getNomeCliente());%></title>
    </head>
    <body>
        <div class="container">
            <div class="main">
                <div class="main-center">
                    <br/>
                    <h1 class="text-center">Informações</h1>
                    <form action="ClientesServlet" method="post">
                        <div class="form-group">
                            <label for="nomeCliente">Nome</label>
                            <div class="input-group">
                                <input readonly type="text" class="form-control" id="nomeCliente" name="nomeCliente" value="<%out.print(cliente.getNomeCliente());%>" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="idCliente">Id</label>
                            <div class="input-group">
                                <input readonly type="text" class="form-control" id="idCliente" name="idCliente" value="<%out.print(cliente.getIdCliente());%>" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="cpfCliente">CPF</label>
                            <div class="input-group">
                                <input readonly type="text" class="form-control" id="cpfCliente" name="cpfCliente" value="<%out.print(cliente.getCpfCliente());%>" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="emailCliente">Email</label>
                            <div class="input-group">
                                <input readonly type="text" class="form-control" id="emailCliente" name="emailCliente" value="<%out.print(cliente.getEmailCliente());%>" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="dataCliente">Data</label>
                            <div class="input-group">
                                <input readonly type="date" class="form-control" id="dataCliente" name="dataCliente" value="<%out.print(cliente.getDataCliente().toString());%>" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="ruaCliente">Rua</label>
                            <div class="input-group">
                                <input readonly type="text" class="form-control" id="ruaCliente" name="ruaCliente" value="<%out.print(cliente.getRuaCliente());%>" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="numCliente">Numero</label>
                            <div class="input-group">
                                <input readonly type="number" class="form-control" id="numCliente" name="numCliente" value="<%out.print(cliente.getNumCliente());%>" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="cepCliente">CEP</label>
                            <div class="input-group">
                                <input readonly type="text" class="form-control" id="cepCliente" name="cepCliente" value="<%out.print(cliente.getCepCliente());%>" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="cidadeCliente">Cidade</label>
                            <div class="input-group">
                                <input readonly type="text" class="form-control" id="cidadeCliente" name="cidadeCliente" value="<%out.print(cliente.getCidadeCliente());%>" />
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="ufCliente">UF</label>
                            <div class="input-group">
                                <input readonly type="text" class="form-control" id="ufCliente" name="ufCliente" value="<%out.print(cliente.getUfCliente());%>" />
                            </div>
                        </div>
                        <br/>
                        <button name="voltar" formaction="ClientesServlet" class="btn btn-light">Voltar</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
