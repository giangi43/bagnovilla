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
        <title>BagnoVilla</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="Author" content="giangi">
        <meta name="Description" content="prenota la tua permanenza nella spiaggia di villasimius">
        <meta name="Keywords" content="spiaggia, stabilimento balneare, home">
        <link rel="stylesheet" type="text/css" href="style.css"  media="screen">
        <script type="text/javascript" src="js/jquery.js"></script>
        <script type="text/javascript" src="js/code.js"></script>
    </head>
    <body>
        <c:set var="page" value="index" scope="request"/>
        <jsp:include page="header.jsp"/>
        <c:set var="postiDisponibiliMattina" value="10" scope="request"/>
        <c:set var="postiDisponibiliSera" value="11" scope="request"/>
        <c:set var="postiTotali" value="20" scope="request"/>
        <main>
            <div class="benvenuti">
                <h2> benvenuti </h2>
                <p id="intro"> 
                    nascosta tra le stradine dell' isola della sardegna si trova una piccola perla di mare chiamata spiaggia di Villasimius.<br>
                    vieni da noi a godere dell'acqua cristallina che solo questa terra ti può donare. 
                </p>
            </div>

            <c:if test="${empty timeSlots}">

                <h2>nessun posto disponibile</h2>
            </c:if>  
            <c:if test="${not empty timeSlots}">
                <div>
                    <h2>Posti disponibili</h2>
                    <c:forEach items="${timeSlots}" var="timeSlot" varStatus="loop"> 

                        <div  class="col-6">
                            <h3 id="date-${loop.index}">${timeSlot.getBooking_date().getDayOfMonth()}</h3>
                            <p id="timeTable-${loop.index}">${timeSlot.getTimeTable()}</p>
                            <p id="emptyPlaces-${loop.index}">${timeSlot.getEmptySpots()}</p>
                            <p id="aviableSpots-${loop.index}">${timeSlot.getAviable_spots()}</p>
                        </div>


                    </c:forEach>
                </div>

            </c:if> 
        </main>
    </body>
</html>
