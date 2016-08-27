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
                  <select name="professor">
                      <option></option>
                      <option></option>
                  </select>
            </div>
            
             <div> 
                 <select name="aluno">
                     <option></option>
                     <option></option>
                 </select>
             </div>
            
             <div> 
                 <input type="text" name="pontos" value="0" />
             </div>
            <input type="submit" value="" />
            
        </form>
        
        
        
    </body>
</html>
