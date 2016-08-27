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
                
                <th>Nome do aluno</th>
                <th>Professor</th>
                <th>Pontos</th>
              </tr>
                
            </thead>
            <tbody>
                <c:forEach var="evento" items="${eventos}">
                <tr>

                    
                        <td>${evento.aluno.nome}</td>
                        <td>${evento.professor.nome}</td>
                        <td>${evento.pontos}</td>
                     
                   
               </tr>
                </c:forEach>  
            </tbody>
    </body>
</html>
