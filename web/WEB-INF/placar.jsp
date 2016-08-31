<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List all</title>
    </head>
    <body>
        <h1>Placar Geral</h1>
        <table border="1px" cellspacing=0 bordercolor=red>
            <thead>
              <tr>
                
                <th>Grupo 1</th>
                <th>Grupo 2</th>
                <th>Grupo 3</th>
                
              </tr>
                
            </thead>
            <tbody>
                <c:forEach var="evento" items="${eventos}">
               
                if(${evento.aluno.grupo}== 1){
                   
                    }
                    
                    
                    
                    
                    
                <tr>
                        <td>${evento.aluno.nome}</td>
                        <td>${evento.professor.nome}</td>
                        <td>${evento.pontos}</td>
               </tr>
                </c:forEach>  
            </tbody>
    </body>
</html>
