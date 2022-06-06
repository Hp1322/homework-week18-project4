package com.herokuapp.model;

import java.util.HashMap;

public class HerokuappPojo {

    private String firstname;
    private String lastname;
    private int totalprice;
    private Boolean depositpaid;
    private HashMap<Object, Object> bookingdates;
    private String username;
    private String password;
    private String additionalneeds;

    public HashMap<Object, Object> getBookingdates() {
        return bookingdates;
    }

    public void setBookingdates(HashMap<Object, Object> bookingdates) {
        this.bookingdates = bookingdates;
    }




    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }

    public Boolean getDepositpaid() {
        return depositpaid;
    }

    public void setDepositpaid(Boolean depositpaid) {
        this.depositpaid = depositpaid;
    }


    public String getAdditionalneeds() {
        return additionalneeds;
    }

    public void setAdditionalneeds(String additionalneeds) {
        this.additionalneeds = additionalneeds;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
