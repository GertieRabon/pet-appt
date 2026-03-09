package com.gabriel.appoms.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private static final Logger log = LoggerFactory.getLogger(NotificationService.class);

    public void processAppointmentNotification(AppointmentEvent event) {

        String message = buildMessage(event);

        log.info("Notification for owner {}: {}", event.getOwnerId(), message);

        // TODO: When Android app is ready, replace the log above with FCM push notification:
        // fcmService.sendPushNotification(event.getOwnerId(), "Appointment Update", message);
    }

    private String buildMessage(AppointmentEvent event) {
        return switch (event.getEventType()) {
            case "CREATED"   -> "Your appointment has been received and is pending approval.";
            case "APPROVED"  -> "Great news! Your appointment has been approved.";
            case "REJECTED"  -> "Unfortunately, your appointment has been rejected.";
            case "COMPLETED" -> "Your grooming appointment is complete. Thank you!";
            case "CANCELLED" -> "Your appointment has been cancelled.";
            default          -> "Your appointment status has been updated to: " + event.getStatus();
        };
    }
}