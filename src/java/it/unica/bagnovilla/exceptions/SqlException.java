/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unica.bagnovilla.exceptions;

import java.sql.SQLException;

/**
 *
 * @author fpw
 */
public class SqlException extends SQLException{
     public SqlException(String errorMessage) {
        super(errorMessage);
    }
}
