<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Evento</title>
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
                <c:forEach var="datas" items="${ld}">
                <tr>
                        <td>${datas}</td>
                        
               </tr>
                </c:forEach>  
           
            </tbody>
        </table>
        </body>
    
</html>

