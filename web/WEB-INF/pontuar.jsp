<%-- 
    Document   : pontuar
    Created on : 21/08/2016, 16:17:49
    Author     : Filipe
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link type="text/css" rel="stylesheet" href="Css/estilo.css">
    </head>
    <body>
        <center>
        <h1>BugTeam</h1>
        Para pontuar um aluno é necessário informar o professor, aluno e a nota:<p>
        <form action="pontuar" method="POST">
            <div>

                <select name="professor" >
                    <option>Informe o professor...</option>
                    <c:forEach var="professor" items="${professores}">
                        <option value="${professor.id}">${professor.nome}</option>
                    </c:forEach> 
                </select>

                <select name="aluno" >
                    <option>Informe o aluno...</option>
                    <c:forEach var="aluno" items="${alunos}">
                        <option value="${aluno.id}">${aluno.nome}</option>
                    </c:forEach> 
                </select>
                              
                <input type="text" name="Cponto" value="" size="4" tabindex="1" placeholder="Pontos" />
           <input type="submit" value="Enviar" />
            </div>
            
       
        </form>
        <button  id="placar" value="acao" name="acao"  onclick="location.href='placar'">Placar</button>
    <button  id="listar" value="acao" name="acao"  onclick="location.href='listar'">Listar todos</button>
        </center>
    </body>
</html>
