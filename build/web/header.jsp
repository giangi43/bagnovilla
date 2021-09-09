<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
 <header>
    <c:if test="${page != 'login'}">
        <a href="index.jsp">
           <img title="Logo" alt="Logo di scootercritic" src="img/logo.png" width="368" height="73">
        </a>
    </c:if>
</header>
<nav>
    <ul>
        <li><c:if test="${page == 'index'}"> <div class="active"> </c:if> <a href="index.jsp">Home</a> <c:if test="${page == 'index'}"> </div> </c:if></li>
        <li><c:if test="${page == 'about'}"> <div class="active"> </c:if> <a href="about.jsp">About</a> <c:if test="${page == 'about'}"> </div> </c:if></li>
        <li><c:if test="${page == 'contatti'}"> <div class="active"> </c:if><a href="contatti.jsp">Contatti</a> <c:if test="${page == 'contatti'}"> </div> </c:if> </li>
        <li><c:if test="${page == 'news'}"> <div class="active"> </c:if><a href="news.jsp">News</a> <c:if test="${page == 'news'}"> </div> </c:if></li>
        <li><c:if test="${page == 'chiSiamo'}"> <div class="active"> </c:if><a href="chisiamo.jsp">Chi siamo</a> <c:if test="${page == 'chiSiamo'}"> </div> </c:if></li>
        <li><c:if test="${page == 'nuovaRecensione'}"> <div class="active"> </c:if><a href="nuovarecensione.jsp">Nuova recensione</a> <c:if test="${page == 'nuovaRecensione'}"> </div> </c:if></li>
        <li><c:if test="${page == 'login'}"> <div class="active"> </c:if>
                <c:if test="${empty user}">
                    <a href="login.jsp">Login</a>
                </c:if>
                <c:if test="${not empty user}"> 
                    <a class="areaPersonale" href="areaPersonale.jsp">Area Personale</a> 
                </c:if>
            <c:if test="${page == 'login'}"> </div> </c:if></li>

    </ul>
</nav>     