<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login JSP</title>
</head>
<body>

<jsp:useBean id="p" class="beans.Pessoa" scope="session" />
<jsp:setProperty name="p" property="*" />
<h2>Sessão armazenada.</h2>
<a href="listar.jsp">Ver sessão</a>

</body>
</html>