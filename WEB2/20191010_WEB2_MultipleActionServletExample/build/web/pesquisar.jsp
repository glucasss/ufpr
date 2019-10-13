<%-- 
    Document   : pesquisar
    Created on : 10/10/2019, 21:20:26
    Author     : lucas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pesquisar</title>
    </head>
    <body>
        <h1>Controladora</h1>
        <form action="Controladora?action=pesquisar" method="POST">
            Texto: <input type="text" name="texto" /><br/>
            <input type="submit" value="Pesquisar" /><br/>
            <input type="reset" value="Limpar" /><br/>
        </form>
    </body>
</html>
