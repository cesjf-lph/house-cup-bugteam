<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List all</title>
        <link type="text/css" rel="stylesheet" href="Css/estilo.css">
    </head>
    <body>
        <center>
        <h1>BugTeam</h1>
        Listar todas as notas por semestre<p>
        <form action="listaPorSem" method="POST">
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
        <table border="1px" cellspacing=0>
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
        </table>
        
    <button  id="placar" value="acao" name="acao"  onclick="location.href='placar'">Placar</button>
    <button  id="listar" value="acao" name="acao"  onclick="location.href='listar'">Listar todos</button>
    <button  id="pontuar" value="acao" name="acao"  onclick="location.href='pontuar'">Pontuar</button>
        </center>
    </body>
</html>
