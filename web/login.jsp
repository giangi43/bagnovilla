<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>BagnoVilla login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="Author" content="giangi">
        <meta name="Description" content="sito di login per bagnanti">
        <meta name="Keywords" content="spiaggia, stabilimento balneare, login">
        <link rel="stylesheet" type="text/css" href="style.css"  media="screen">

    </head>
    <body>
        <c:if test="${not empty user}">
            <c:redirect url="personalArea.jsp"/>
        </c:if>
        <c:if test="${empty user}">
        <c:set var="page" value="login" scope="request"/>
        <jsp:include page="header.jsp"/>
        <main id="login">
            <a href="index.jsp">
                <img title="Logo" alt="Logo di Bagnovilla" src="img/logo.png" width="368" height="73">
            </a>
            <h1>Login</h1>
            <fieldset>
                <legend> insert data: </legend>
                <form action="LoginServlet" method="post">
                    <label for="username"> Username</label>
                    <input type="text" id="username" name="username" />
                    <label for="password"> Password</label>
                    <input type="password" id="password" name="password" />
                    <input type="hidden" id="origine" name="origine" value="login.jsp"/> 

                    <input type="submit" value="login" />                
                </form>
            </fieldset>
            <a href="signup.jsp"> <b>signup</b> </a>
        </main>
        </c:if>
    </body>
</html>
