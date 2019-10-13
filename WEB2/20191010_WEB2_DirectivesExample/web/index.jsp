<%-- 
    Document   : index
    Created on : 10/10/2019, 19:27:17
    Author     : lucas
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Teste de diretiva</title>
    </head>
    <body>
        <h1>Página HTML - Exemplo de diretriz</h1>
        <%
            ArrayList<Integer> arr = new ArrayList<>();
            arr.add(10);
            arr.add(20);
            arr.add(30);
            
            for(Integer i : arr){
                out.println("<h2>" + i + "</h2>");
            }
        %>
    </body>
</html>
