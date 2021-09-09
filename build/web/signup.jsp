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
       <c:set var="page" value="signup" scope="request"/>
        <jsp:include page="header.jsp"/>
        <main id="login">
            <a href="index.jsp">
                <img title="Logo" alt="Logo di Bagnovilla" src="img/logo.png" width="368" height="73">
            </a>
            <h1>Login</h1>
            <fieldset>
                <legend> insert data: </legend>
                <form action="SignUpServlet" method="post">
                    <label for="user"> Username</label>
                    <input type="text" id="user" name="user" />
                    
                    <label for="pass"> Password</label>
                    <input type="password" id="pass" name="pass" />
                    
                    <label for="pass"> repeat Password</label>
                    <input type="password" id="rpass" name="rpass" />
                    
                    <label for="name"> name </label>
                    <input type="text" id="name" name="name" />
                    
                    <label for="last name"> last name </label>
                    <input type="text" id="last_name" name="last_name" />
                    
                    <label for="birth_date"> birth date </label>
                    <input type="date" id="birth_date" name="birth_date" />
                    
                    <label for="fiscal_code"> fiscal code </label>
                    <input type="text" id="fiscal_code" name="fiscal_code" />
                    
                    <label for="sex"> sex </label>                    
                    <input type="radio" id="male" name="sex" value="male">
                    <label for="male">male</label><br>
                    <input type="radio" id="female" name="sex" value="female">
                    <label for="female">female</label><br>
                    
                    <label for="email"> email </label>
                    <input type="text" id="email" name="email" />
                    
                    <label for="telephone_number"> telephone number </label>
                    <input type="text" id="telephone_number" name="telephone_number" />
                    
                    <label for="invoice"> you want the invoice? </label>                    
                    <input type="radio" id="male" name="invoice" value="yes">
                    <label for="yes">yes</label><br>
                    <input type="radio" id="female" name="invoice" value="no">
                    <label for="no">no</label><br>
                    
                    
                    <input type="hidden" id="origine" name="origine" value="signup.jsp"/> 

                    <input type="submit" value="signup" />                
                </form>
            </fieldset>
        </main>
    </body>
</html>
