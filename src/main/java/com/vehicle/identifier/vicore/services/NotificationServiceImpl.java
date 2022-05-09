package com.vehicle.identifier.vicore.services;

import com.vehicle.identifier.vicore.models.Notification;
import com.vehicle.identifier.vicore.models.Vehicle;
import com.vehicle.identifier.vicore.repositories.NotificationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.vehicle.identifier.vicore.util.Notifications.registrationNotification;

@Component
public class NotificationServiceImpl implements NotificationService{

    @Autowired
    NotificationRepo notificationRepo;

    @Override
    public boolean sendNotification(Notification notification) {
        notificationRepo.save(notification);

        return false;
    }

    @Override
    public void sendRegistrationEmail(Vehicle vehicle) {
        Notification notification = registrationNotification(vehicle);

    }

    @Override
    public void sendEmailNotification(Notification notification) {
    }
}
