/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unica.bagnovilla.model.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author fpw
 */
public class TimeSlot {

    private LocalDate booking_date;
    private boolean is_morning;
    private int aviable_spots;
    private int idLifeguard;
    private int emptySpots;

    public int getEmptySpots() {
        return emptySpots;
    }

    public void setEmptySpots(int emptySpots) {
        this.emptySpots = emptySpots;
    }
    
    public LocalDate getBooking_date() {
        return booking_date;
    }

    public void setBooking_date(LocalDate booking_date) {
        this.booking_date = booking_date;
    }

    public boolean isIs_morning() {
        return is_morning;
    }

    public void setIs_morning(boolean is_morning) {
        this.is_morning = is_morning;
    }

    public int getAviable_spots() {
        return aviable_spots;
    }

    public void setAviable_spots(int aviable_spots) {
        this.aviable_spots = aviable_spots;
    }

    public int getIdLifeguard() {
        return idLifeguard;
    }

    public void setIdLifeguard(int idLifeguard) {
        this.idLifeguard = idLifeguard;
    }
    
    public String getTimeTable(){
        if(is_morning){
            return "8:00-14:00";
        }
        return "14:00-20:00";        
    }

}
