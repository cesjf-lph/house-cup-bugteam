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
    </head>
    <body>
        <form action="criar" method="get">
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
                              
                <input type="text" name="Cponto" value="0" size="3" />
           
            </div>
            <input type="submit" value="Enviar" />
       
        </form>
    </body>
</html>
