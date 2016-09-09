<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Event</title>
    </head>
    <body>
        <h1>Eventos do sistema</h1>
        <table border="1px" cellspacing=0 bordercolor=red>
            <thead>
              <tr>
                
                <th>Nome do aluno</th>
                <th>Professor</th>
                <th>Data do evento</th>
              </tr>
                
            </thead>
            <tbody>
                <c:forEach var="evento" items="${eventos}">
                <tr>
                        <td>${evento.aluno.nome}</td>
                        <td>${evento.professor.nome}</td>
                        <td>${evento.data}</td>
               </tr>
                </c:forEach>  
            </tbody>
    </body>
</html>
