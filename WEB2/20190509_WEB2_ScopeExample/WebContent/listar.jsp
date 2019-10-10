<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Listar JSP</title>
</head>
<body>

<jsp:useBean id="p" class="beans.Pessoa" scope="session" />
<h2>Dados da Sessão</h2>
Nome: <jsp:getProperty name="p" property="nome" /><br/>
Endereco: <jsp:getProperty name="p" property="endereco" /><br/>

</body>
</html>