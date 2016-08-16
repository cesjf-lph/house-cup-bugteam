<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List all</title>
    </head>
    <body>
        <h1>Alunos</h1>
        <c:forEach var="candidato" items="${candidatos}">
            <p><label>Nome: </label><c:out value="${candidato.nomeCompleto}" /></p>
            <p><label>Idade: </label><c:out value="${candidato.idade}" /></p>
            <p><label>Sexo: </label><c:out value="${candidato.sexo ? 'Masculino' : 'Feminino'}" /></p>
            <p><label>Data Inscrição: </label><c:out value="${candidato.inscricao}" /></p>
            

            <hr />
        </c:forEach>
    </body>
</html>
