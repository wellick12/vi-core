package com.vehicle.identifier.vicore.util;

import com.vehicle.identifier.vicore.models.Driver;
import com.vehicle.identifier.vicore.models.Notification;
import com.vehicle.identifier.vicore.models.Timelog;
import com.vehicle.identifier.vicore.models.Vehicle;

public class Notifications {


    public static Notification arrivalNotification(Timelog timeLog, Driver driver){

        String message = "Good day" + driver.getFirstname() + driver.getLastname() +
                "<br> Welcome to Harare Institute of Technology <br> Your entry time is:" +
                timeLog.getLoggedIn() + "<br>";

         Notification notification = new Notification();

        notification.setId(Util.uuid());
        notification.setCreated(Util.currentTime());
        notification.setUpdated(Util.currentTime());
        notification.setDestination(driver.getEmail());
        notification.setType("Welcome to HIT");
        notification.setMessage(message);

        return notification;
    }

    public static Notification departureNotification(Timelog timeLog, Driver driver){

        String message = "Good day" + driver.getFirstname() + driver.getLastname() +
                "<br>We hope you have fruitful day at HIT!! <br> Your exit time was:" +
                timeLog.getLoggedIn() + "<br>";

        Notification notification = new Notification();

        notification.setId(Util.uuid());
        notification.setCreated(Util.currentTime());
        notification.setUpdated(Util.currentTime());
        notification.setDestination(driver.getEmail());
        notification.setType("Thank you for your visit");
        notification.setMessage(message);

        return notification;
    }



    public static Notification blockedNotification(Timelog timeLog){

        String message = "Good day Admin" +
                "<br>A blacklisted number plate is attempting to enter the<br>Attempt entry time is:" +
                timeLog.getLoggedIn() + "<br>";

        Notification notification = new Notification();

        notification.setId(Util.uuid());
        notification.setCreated(Util.currentTime());
        notification.setUpdated(Util.currentTime());
        notification.setDestination("vimurungu@gmail.com");
        notification.setType("Security Alert!!");
        notification.setMessage(message);

        return notification;
    }


    public static Notification registrationNotification(Vehicle vehicle){
        String message = "Good day Admin" +
                "<br> New a new vehicle with license plate" + vehicle.getLicensePlate() +
                "has been added to the database. " +
                "Please add driver details to the associated vehicle on the following URL " +
                "<br> localhost:8080/register/driver";
        Notification notification = new Notification();

        notification.setId(Util.uuid());
        notification.setCreated(Util.currentTime());
        notification.setUpdated(Util.currentTime());
        notification.setType("Driver Registration request");
        notification.setDestination("vimurungu@gmail.com");
        notification.setMessage(message);
        return notification;
    }
}
