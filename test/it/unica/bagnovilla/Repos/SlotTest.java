/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unica.bagnovilla.Repos;

import it.unica.bagnovilla.Repos.BookingRepo;
import it.unica.bagnovilla.Models.CommonResponse;
import it.unica.bagnovilla.Models.UserModel;
import it.unica.bagnovilla.model.entity.TimeSlot;
import java.time.LocalDate;
import java.util.ArrayList;
import org.testng.Assert;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author fpw
 */
public class SlotTest {
    
    public SlotTest() {
    }
    
    @Test
    public void testSlot() {
        System.out.println("test add slot");
        
        TimeSlot reservation = new TimeSlot(LocalDate.parse("2021-08-02"), false, 5);
        CommonResponse result = BookingRepo.getInstance().addSlot(reservation);
        Assert.assertTrue(result.result, "eh no");
    }
    
    @Test
    public void testGetSlots() {
        System.out.println("test get slots");
        
        LocalDate August = LocalDate.parse("2021-08-01");
        CommonResponse result = BookingRepo.getInstance().getSlotCalendar(August);
        
        Assert.assertTrue(result.result && !((ArrayList<TimeSlot>)result.payload).isEmpty(), "eh no");
    }
    
}
