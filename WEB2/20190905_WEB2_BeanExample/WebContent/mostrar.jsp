<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Teste de bean</title>
</head>
<body>

<jsp:useBean id="p" class="beans.Pessoa" />
<jsp:setProperty name="p" property="*" />
<h1>Processado</h1>
Nome: <jsp:getProperty name="p" property="nome" /><br/>
Endereco: <jsp:getProperty name="p" property="endereco" /><br/>

</body>
</html>