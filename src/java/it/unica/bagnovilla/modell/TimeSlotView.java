/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unica.bagnovilla.modell;

import it.unica.bagnovilla.Models.*;
import java.time.LocalDate;

/**
 * Questa classe mi serve per mostrare gli slot nel front end in modo comodo e
 * nasce con questa esigenza. Si differenzia da Slot in quanto rappresenta
 * il database.
 * @author fpw
 */
public class TimeSlotView { //slotViewModel
    public int day;     // giorno del mese come intero
    public int morningEmptySpots;   // numero di posti relativi alla mattina
    public int eveningEmptySpots;   // numero di posti relativi alla sera
    
    
    public int getDay() {
        return day;
    }

    public int getNumAm() {
        return morningEmptySpots;
    }

    public int getNumPm() {
        return eveningEmptySpots;
    }
    
    

    public TimeSlotView() {
    }

    public TimeSlotView(int day, int numAm, int numPm) {
        this.day = day;
        this.morningEmptySpots = numAm;
        this.eveningEmptySpots = numPm;
    }
    
    
}
