<%-- 
    Document   : areaPersonale
    Created on : Apr 23, 2021, 7:12:10 AM
    Author     : fpw
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Personal Area</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="Author" content="giangi">
        <meta name="Description" content="sito per aggiungere recensioni sui monopattini">
        <meta name="Keywords" content="monopattini, belli, recensioni">
        <link rel="stylesheet" type="text/css" href="style.css"  media="screen">
    </head>
    <body>
        
        <c:if test="${empty user}">
            <c:redirect url="login.jsp"/>
        </c:if>
        <c:if test="${not empty user}">
        <c:set var="page" value="login" scope="request"/>
        <jsp:include page="header.jsp"/>
        <main>
            <h1>ciao ${user}!</h1>
            
        </main>        
        </c:if>
    </body>
</html>
