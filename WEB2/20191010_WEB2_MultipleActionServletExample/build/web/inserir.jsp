<%-- 
    Document   : index
    Created on : 10/10/2019, 21:16:16
    Author     : lucas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inserir</title>
    </head>
    <body>
        <h1>Controladora</h1>
        <form action="Controladora?action=inserir" method="POST">
            Nome: <input type="text" name="nome" /><br/>
            E-mail: <input type="text" name="email" /> <br/>
            <input type="submit" value="Inserir" />
            <input type="reset" value="Limpar" />
        </form>
    </body>
</html>
