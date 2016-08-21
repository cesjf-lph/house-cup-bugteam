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
        <table border="1px" cellspacing=0 bordercolor=red>
            <thead>
              <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Periodo</th>
                <th>Pontos</th>
              </tr>
                
            </thead>
            <tbody>
                <c:forEach var="aluno" items="${alunos}">
                <tr>

                    
                        <td>${aluno.id}</td>
                        <td>${aluno.nome}</td>
                        <td>${aluno.periodo}</td>
                        <td>${aluno.pontos}</td>
                   
               </tr>
                </c:forEach>  
            </tbody>
    </body>
</html>
