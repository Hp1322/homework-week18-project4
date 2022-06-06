package com.herokuapp.herokuappinfo;

import com.herokuapp.testbase.TestBase;
import com.herokuapp.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class HerokuAppCURDTestWithSteps extends TestBase {

    static String firstname = "Jim" + TestUtils.getRandomValue();
    static String lastName = "Brown" + TestUtils.getRandomValue();
    static int totalPrice = 110;
    static Boolean depositpaid = true;
    static String username = "Rom" + TestUtils.getRandomValue();
    static String password = "DN" + TestUtils.getRandomValue();
    static String additionalneeds = "Breakfast";
    static int bookingID;


    @Steps
    HerokuAppSteps herokuAppSteps;

    @Title("This will create a new booking")
    @Test
    public void test001(){
        HashMap<Object,Object> bookingdates = new HashMap<>();
        bookingdates.put("checkin", "2018-01-01");
        bookingdates.put("checkOut", "2019-01-01");

        ValidatableResponse response = herokuAppSteps.createBooking(firstname,lastName,totalPrice,depositpaid,bookingdates,additionalneeds);
        response.log().all().statusCode(200);
        bookingID = response.log().all().extract().path("bookingid");
        System.out.println(bookingID);
    }
    @Title("Verify if the booking was added to the list")
    @Test
    public void test002() {
        HashMap<String, Object> bookingMap = herokuAppSteps.getBookingMapInfoByID(bookingID);
        Assert.assertThat(bookingMap, hasValue(firstname));
        System.out.println(bookingMap);

    }

    @Title("Update the store information and verify updated information")
    @Test
    public void test003() {
        HashMap<Object,Object> bookingdates = new HashMap<>();
        bookingdates.put("checkin", "2018-01-01");
        bookingdates.put("checkOut", "2019-01-01");

        firstname = firstname + "_updated";
        herokuAppSteps.updateBooking(bookingID,firstname,lastName,totalPrice,depositpaid,bookingdates,additionalneeds).log().all().statusCode(200);
        HashMap<String, Object> bookingMap = herokuAppSteps.getBookingMapInfoByID(bookingID);
        Assert.assertThat(bookingMap, hasValue(firstname));
    }

    @Title("Delete the booking and verify if the list is deleted!")
    @Test
    public void test004() {
        herokuAppSteps.deleteBooking(bookingID).statusCode(201);
        herokuAppSteps.getBookingById(bookingID).statusCode(404);
    }

}
