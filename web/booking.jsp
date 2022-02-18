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
        <title>Booking</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="Author" content="giangi">
        <meta name="Description" content="sito di prenotazione per bagnanti">
        <meta name="Keywords" content="spiaggia, stabilimento balneare, login">
        <link rel="stylesheet" type="text/css" href="style.css"  media="screen">

    </head>
    <body>
    <%--    <c:if test="${empty user}">
            <c:redirect url="login.jsp"/>
        </c:if>
        <c:if test="${not empty user}">
    --%>
       <c:set var="page" value="booking" scope="request"/>
        <jsp:include page="header.jsp"/>
        <c:set var="postiTotali" value="20" scope="request"/>
        <main id="login">
            <a href="index.jsp">
                <img title="Logo" alt="Logo di Bagnovilla" src="img/logo.png" width="368" height="73">
            </a>
            <h1>Login</h1>
            <fieldset>
                <legend> insert data: </legend>
                <form action="BookingServlet" method="post">
                    <label for="booking_date"> booking date </label>
                    <input type="date" id="booking_date" name="booking_date" />
                    
                    <label for="quantity">Quantity (between 1 and ${postiTotali}) ++++ :</label>
                    <input type="number" id="quantity" name="quantity" min="1" max="${postiTotali}">
                    
                    <label for="period"> period </label>                    
                    <input type="radio" id="morning" name="period" value="morning">
                    <label for="morning">morning 8-14</label><br>
                    <input type="radio" id="evening" name="period" value="evening">
                    <label for="evening">evening 14-20</label><br>

                    <input type="submit" value="book_it" />                
                </form>
            </fieldset>
        </main>
        <%--</c:if> --%>
    </body>
</html>