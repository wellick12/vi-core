package com.vehicle.identifier.vicore.controllers;

import com.vehicle.identifier.vicore.models.*;
import com.vehicle.identifier.vicore.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static com.vehicle.identifier.vicore.util.Notifications.*;
import static com.vehicle.identifier.vicore.util.Times.entryTime;

@Controller
public class RemoteController {

    private static final String MAIN_ENTRANCE_CAMERA = "MAIN_ENTRANCE_CAMERA";
    private static final String MAIN_EXIT_CAMERA = "MAIN_EXIT_CAMERA";
    @Autowired
    VehicleService vehicleService;

    @Autowired
    GateService gateService;

    @Autowired
    NotificationService notificationService;

    @Autowired
    DriverService driverService;

    @Autowired
    SettingService settingService;

    @Autowired
    TimelogService timelogService;

    @RequestMapping(method = RequestMethod.POST, value = "/remote",
            consumes = "application/json", produces = "application/json")
    public ResponseEntity getVehicleFromRemote(@RequestBody Vehicle vehicle) {

        Setting mainEntrance = settingService.getSettingByKey(MAIN_ENTRANCE_CAMERA);


        if (Boolean.parseBoolean(mainEntrance.getValue())) {

            Vehicle vehicle1 = vehicleService.getVehicleByLicensePlate(vehicle.getLicensePlate());
            Timelog timelog = entryTime(vehicle.getLicensePlate());
            timelogService.addTimeEntry(timelog);

            if (vehicle1.blacklisted) {

                Notification notification = blockedNotification(timelog);
                System.out.println("Sending email");
                notificationService.sendEmailNotification(notification);
            }


            if (!checkIfVehicleExists(vehicle.getLicensePlate())) {
                vehicleService.addFromRemoteVehicle(vehicle);
                gateService.openGate();
                System.out.println("Vehicle Saved");
                timelogService.addTimeEntry(entryTime(vehicle.getLicensePlate()));
                System.out.println("Time Saved");
                notificationService.sendRegistrationEmail(vehicle);
                return ResponseEntity.ok().build();
            }
            Driver driver = driverService.findById(vehicle1.getDriverId());
            gateService.openGate();


            Notification notification = arrivalNotification(timelog, driver);
            System.out.println("Sending email");
            notificationService.sendEmailNotification(notification);

            return ResponseEntity.ok().build();

        }

        Setting mainExit = settingService.getSettingByKey(MAIN_EXIT_CAMERA);

        if (Boolean.parseBoolean(mainExit.getValue())) {



            Vehicle vehicle1 = vehicleService.getVehicleByLicensePlate(vehicle.getLicensePlate());
            Timelog timelog = entryTime(vehicle.getLicensePlate());
            timelogService.addTimeEntry(timelog);
            Driver driver = driverService.findById(vehicle1.getDriverId());
            gateService.openGate();
            Notification notification = departureNotification(timelog, driver);
            System.out.println("Sending email");
            notificationService.sendEmailNotification(notification);

            return ResponseEntity.ok().build();

        }

        return null;
    }

    public boolean checkIfVehicleExists(String licencePlate) {
        Vehicle vehicle = vehicleService.getVehicleByLicensePlate(licencePlate);
        if (vehicle == null || vehicle.getId().isEmpty() || vehicle.getId() == null) {
            return false;
        }
        return true;
    }
}
