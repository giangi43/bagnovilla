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
                <h2>Posti disponibili</h2>
            </div>
            <div  class="col-6">
                <article class="posti_disponibili">
                    <h3 class="stats">orario 8 - 14</h3>
                    <p class="stats"> <b>posti disponibili:</b> ${postiDisponibiliMattina}  <b>posti totali:</b> ${postiTotali} </p>
                </article>
            </div>
            
            <div class="col-6">
                <article class="posti_disponibili">
                    <h3 class="stats">orario 14 - 20</h3>
                    <p class="stats"> <b>posti disponibili:</b> ${postiDisponibiliSera}  <b>posti totali:</b> ${postiTotali} </p>
                </article>
            </div>
            
        </main>
    </body>
</html>
