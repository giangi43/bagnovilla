/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unica.bagnovilla.model;

import java.time.LocalDateTime;

/**
 *
 * @author fpw
 */
public class TimeSlot {
    private long id;
    private LocalDateTime booking_date;
    private boolean is_morning;
    private int aviable_spots;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getBooking_date() {
        return booking_date;
    }

    public void setBooking_date(LocalDateTime booking_date) {
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
}
