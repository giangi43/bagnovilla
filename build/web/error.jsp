<%-- 
    Document   : error
    Created on : Apr 22, 2021, 5:28:12 AM
    Author     : fpw
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Error</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="Author" content="giangi">
        <meta name="Description" content="novitÃ  sull' bel mondo dei monopattini">
        <meta name="Keywords" content="monopattini, belli, recensioni">
        <link rel="stylesheet" type="text/css" href="style.css"  media="screen">
    </head>
    <body>
        <header>
            <a href="index.html">
                <img title="Logo" alt="Logo di scootercritic" src="img/logo.png" width="368" height="73">
            </a>
        </header>
        <nav>
            <ul>
                <li><a href="index.jsp">Home</a> </li>
                <li><a href="about.jsp">About</a> </li>
                <li><a href="contatti.jsp">Contatti</a> </li>
                <li><a href="news.jsp">News</a> </li>
                <li><a href="chisiamo.jsp">Chi siamo</a> </li>
                <li><a href="login.jsp">Login</a> </li>
                <li><a href="nuovarecensione.jsp">Nuova recensione</a> </li>
                
            </ul>
        </nav>
        <main>
            <h1>Error: ${errorMessage}</h1>
            <h2><a href=${origine}>Ritenta, sarai piu' fortunato</a>
        </main>
    </body>
</html>
