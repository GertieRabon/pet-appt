package com.gabriel.appoms.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class AppointmentConsumer {

    private static final Logger log = LoggerFactory.getLogger(AppointmentConsumer.class);

    private final NotificationService notificationService;

    public AppointmentConsumer(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @KafkaListener(topics = "appointments", groupId = "pet-grooming-group")
    public void consumeAppointmentEvent(AppointmentEvent event) {
        log.info("Appointment event received: type={}, appointmentId={}, status={}",
                event.getEventType(), event.getAppointmentId(), event.getStatus());
    }

    @KafkaListener(topics = "notifications", groupId = "pet-grooming-group")
    public void consumeNotificationEvent(AppointmentEvent event) {
        log.info("Processing notification for owner {}, appointment {}",
                event.getOwnerId(), event.getAppointmentId());
        notificationService.processAppointmentNotification(event);
    }
}