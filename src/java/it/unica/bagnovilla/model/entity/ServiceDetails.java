/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unica.bagnovilla.model.entity;

import java.time.LocalDateTime;

/**
 *
 * @author fpw
 */
public class ServiceDetails {
    private int id;
    private LocalDateTime bookingDateTime;
    private boolean isMorning;
    private double price;
    private int bookedPlaces;
    private String serviceDescription;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getBookingDateTime() {
        return bookingDateTime;
    }

    public void setBookingDateTime(LocalDateTime bookingDateTime) {
        this.bookingDateTime = bookingDateTime;
    }

    public boolean isIsMorning() {
        return isMorning;
    }

    public void setIsMorning(boolean isMorning) {
        this.isMorning = isMorning;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getBookedPlaces() {
        return bookedPlaces;
    }

    public void setBookedPlaces(int bookedPlaces) {
        this.bookedPlaces = bookedPlaces;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }
    
    
}
