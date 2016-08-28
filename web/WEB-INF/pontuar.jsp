<%-- 
    Document   : pontuar
    Created on : 21/08/2016, 16:17:49
    Author     : Filipe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="/pontuar">
            <div>

                <select name="professor" >
                    <c:forEach var="professor" items="${Professores}">
                        <option value="${professor.id}">${professor.nome}</option>
                    </c:forEach> 
                </select>

                <select name="aluno" >

                </select>
                              
                <input type="text" name="Cponto" value="0" size="3" />
           
            </div>
            <input type="submit" value="Enviar" name="Enviar" />
       
        </form>
    </body>
</html>
