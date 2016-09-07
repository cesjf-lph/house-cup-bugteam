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
                    <th>Grupos</th>
                    <th>Pontos</th>
                </tr>

            </thead>
             <tbody>
            <c:forEach var="dado" items="${pg}">
                <tr>
                    <td>${dado[0]}</td>
                    <td>${dado[1]}</td>

                </tr>
            </c:forEach>      
            </tbody>
        </table>
    </body>
</html>
