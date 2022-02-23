/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unica.bagnovilla.model.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author fpw
 */
public class User {
    private String username;
    private boolean want_invoice;
    private boolean is_admin;
    private String name;
    private String last_name;
    private LocalDate birth_date;
    private String fiscal_code;
    private String sex;
    private String email;
    private String telephone;
    private String password;
    private String foto;
    
    public String[] getAllAttributesNames(){
        String[] attributes = new String[]
        {"username",
        "want_invoice",
        "is_admin",
        "name",
        "last_name",
        "birth_date",
        "fiscal_code",
        "sex",
        "email",
        "telephone",
        "password",
        "foto"};
        return attributes;
    }
    
    public Object[] getUserAsArray(){
        Object[] o = new Object[12];
        o[0] = username;
        o[1] = want_invoice;
        o[2] = is_admin;
        o[3] = name;
        o[4] = last_name;
        o[5] = birth_date;
        o[6] = fiscal_code;
        o[7] = sex;
        o[8] = email;
        o[9] = telephone;
        o[10] = password;
        o[11] = foto;
        
        return o;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isWant_invoice() {
        return want_invoice;
    }

    public void setWant_invoice(boolean want_invoice) {
        this.want_invoice = want_invoice;
    }

    public boolean isIs_admin() {
        return is_admin;
    }

    public void setIs_admin(boolean is_admin) {
        this.is_admin = is_admin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public LocalDate getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(LocalDate birth_date) {
        this.birth_date = birth_date;
    }

    public String getFiscal_code() {
        return fiscal_code;
    }

    public void setFiscal_code(String fiscal_code) {
        this.fiscal_code = fiscal_code;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.username);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return true;
    }

    
    
    
}
