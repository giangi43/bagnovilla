<%-- 
    Document   : timeSlotJSON
    Created on : Feb 23, 2022, 3:35:33 PM
    Author     : fpw
--%>

<%@page contentType="application/json" pageEncoding="UTF-8"%>
<%@taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<json:array name="items" var="timeSlot" items="${timeSlots}">
    <json:object>
        <json:property name="date" value="${timeSlot.getBooking_date().getDayOfMonth()}"/>
        <json:property name="timeTable" value="${timeSlot.getTimeTable()}"/>
        <json:property name="emptyPlaces" value="${timeSlot.getEmptySpots()}"/>
        <json:property name="aviableSpots" value="${timeSlot.getAviable_spots()}"/>
    </json:object>
</json:array>
