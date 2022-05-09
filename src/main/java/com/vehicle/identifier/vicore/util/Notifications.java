package com.vehicle.identifier.vicore.util;

import com.vehicle.identifier.vicore.models.Driver;
import com.vehicle.identifier.vicore.models.Notification;
import com.vehicle.identifier.vicore.models.Timelog;
import com.vehicle.identifier.vicore.models.Vehicle;

public class Notifications {

    static Notification notification = new Notification();

    public static Notification arrivalNotification(Timelog timeLog, Driver driver){

        String message = "Good day" + driver.getFirstname() + driver.getLastname() +
                "<br /> Welcome to Harare Institute of Technology <br /> Your entry time is:" +
                timeLog.getLoggedIn();

        notification.setId(Util.uuid());
        notification.setCreated(Util.currentTime());
        notification.setUpdated(Util.currentTime());
        notification.setDestination(driver.getMsisdn());
        notification.setMessage(message);

        return notification;
    }

    public static Notification registrationNotification(Vehicle vehicle){
        String message = "Good day Admin" +
                "<br /> New a new vehicle with license plate" + vehicle.getLicensePlate() +
                "has been added to the database. " +
                "Please add driver details to the associated vehicle on the following URL " +
                "<br /> localhost:8080/register/driver";

        notification.setId(Util.uuid());
        notification.setCreated(Util.currentTime());
        notification.setUpdated(Util.currentTime());
        notification.setDestination("vimurungu@gmail.com");
        notification.setMessage(message);
        return notification;
    }
}
