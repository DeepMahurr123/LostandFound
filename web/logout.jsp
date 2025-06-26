<%-- 
    Document   : logout
    Created on : 14 May 2025, 4:38:31 pm
    Author     : deepr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
    response.setHeader("Pragma", "no-cache"); // HTTP 1.0
    response.setDateHeader("Expires", 0); // Proxies
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% 
            HttpSession sess=request.getSession(false);
            if(sess!= null)
            {
                sess.invalidate();
            }
            
            //response.sendRedirect("login.html");
        
        %>
        <script>
            alert('you are logout successfully!..')
            window.location='login.html'
        </script>
    </body>
</html>
