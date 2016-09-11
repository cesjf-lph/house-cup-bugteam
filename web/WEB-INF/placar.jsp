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
        <form action="placar" method="POST">
            <select name="semestre">
                <option>Selecione o periodo</option>
                <option value="0">1° Semetre de 2014</option>
                <option value="1">2° Semetre de 2014</option>
                <option value="2">1° Semetre de 2015</option>
                <option value="3">2° Semetre de 2015</option>
                <option value="4">1° Semetre de 2016</option>
                <option value="5">2° Semetre de 2016</option>
                <option value="6">1° Semetre de 2017</option>
                <option value="7">1° Semetre de 2017</option>
                <option value="8">1° Semetre de 2018</option>
                <option value="9">2° Semetre de 2018</option>
            </select>
            <input type="submit" value="Pesquisar" />
        </form>
    </body>
</html>
