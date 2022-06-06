package com.herokuapp.herokuappinfo;

import com.herokuapp.constants.EndPoints;
import com.herokuapp.model.HerokuappPojo;

import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class HerokuAppSteps {

    @Step("Creating booking with firstname : {0}, lastname: {1}, totalprice : {2}, depositpaid : {3}, bookingdates : {4} and additionalneeds : {5}")
    public ValidatableResponse createBooking(String firstname, String lastname, int totalprice, Boolean depositpaid, HashMap<Object, Object> bookingdates,
                                             String additionalneeds) {
        HerokuappPojo herokuappPojo = new HerokuappPojo();
        herokuappPojo.setFirstname(firstname);
        herokuappPojo.setLastname(lastname);
        herokuappPojo.setTotalprice(totalprice);
        herokuappPojo.setDepositpaid(depositpaid);
        herokuappPojo.setBookingdates(bookingdates);
        herokuappPojo.setAdditionalneeds(additionalneeds);

        return SerenityRest.given()
                .header("Content-Type", "application/json")
                .body(herokuappPojo)
                .when()
                .post("/booking")
                .then();
    }

    @Step("Getting the booking information with bookingID : {0}")
    public HashMap<String, Object> getBookingMapInfoByID(int bookingID){
        HashMap<String, Object> bookingMap = SerenityRest.given().log().all().
                when()
                .pathParam("bookingID", bookingID)
                .auth().preemptive().basic("admin","password123")
                .get(EndPoints.GET_SINGLE_BOOKING_BY_ID)
                .then()
                .statusCode(200)
                .extract().path("");
        return bookingMap;
    }
    @Step("Creating booking with bookingID {0}, firstname : {1}, lastname: {2}, totalprice : {3}, depositpaid : {4}, bookingdates : {5} and additionalneeds : {6}")
    public ValidatableResponse updateBooking(int bookingID,String firstname, String lastname, int totalprice, Boolean depositpaid, HashMap<Object, Object> bookingdates,
                                             String additionalneeds) {
        HerokuappPojo herokuappPojo = new HerokuappPojo();
        herokuappPojo.setFirstname(firstname);
        herokuappPojo.setLastname(lastname);
        herokuappPojo.setTotalprice(totalprice);
        herokuappPojo.setDepositpaid(depositpaid);
        herokuappPojo.setBookingdates(bookingdates);
        herokuappPojo.setAdditionalneeds(additionalneeds);

        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                //   .header("Authorization","Basic "+encoded)
                .auth().preemptive().basic("admin","password123")
                .body(herokuappPojo)
                .pathParam("bookingID",bookingID)
                .when()
                .put(EndPoints.UPDATE_BOOKING_BY_ID)
                .then();
    }

    @Step("Deleting booking information with userID : {0}")
    public ValidatableResponse deleteBooking(int bookingID){
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                //.header("Authorization","Basic "+encoded)
                .auth().preemptive().basic("admin","password123")
                .pathParam("bookingID", bookingID)
                .when()
                .delete(EndPoints.DELETE_BOOKING_BY_ID)
                .then();
    }
    @Step("Getting booking information with userID: {0}")
    public ValidatableResponse getBookingById(int bookingID){
        return SerenityRest.given().log().all()
                .auth().preemptive().basic("admin","password123")
                .pathParam("bookingID", bookingID)
                .when()
                .get(EndPoints.GET_SINGLE_BOOKING_BY_ID)
                .then();
    }
}
