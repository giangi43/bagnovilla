/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unica.bagnovilla.utils;

import it.unica.bagnovilla.exceptions.InvalidParamException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.TimeZone;
import java.sql.Date;

public class Utils {
    
    public static void checkString(String param, int min, int max) throws InvalidParamException{
        
        if(param == null)
            throw new InvalidParamException("Parametro nullo");
        
        if(param.length() < min || param.length() > max)
            throw new InvalidParamException("Stringa non valida: "
                    + "deve avere una dimensione compresa tra "
                    + min + " e " + max + " caratteri.");
    }
    
    public static void checkInteger(String param, int min, int max) throws InvalidParamException{
    
        try{
            int valore = Integer.valueOf(param);
            if(valore<min || valore>max)
                throw new InvalidParamException("Numero non valido: "
                        + "deve essere compreso tra " + min + " e " + max);
        } catch(NumberFormatException e){
            throw new InvalidParamException("La stringa non rappresenta un numero intero");
        }
    }
    
    
    
}