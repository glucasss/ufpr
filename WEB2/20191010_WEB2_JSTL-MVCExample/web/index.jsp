<%-- 
    Document   : index
    Created on : 10/10/2019, 20:20:48
    Author     : lucas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Index</title>
    </head>
    <body>
        <form action="Processa" method="POST">
            Text: <input type="text" name="texto" /><br/>
            <input type="submit" value="Pesquisar" />
            <input type="reset" value="Limpar" />
        </form>
    </body>
</html>
