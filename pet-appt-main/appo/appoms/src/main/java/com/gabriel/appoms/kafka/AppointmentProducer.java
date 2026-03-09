package com.gabriel.appoms.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class AppointmentProducer {

    private static final Logger log = LoggerFactory.getLogger(AppointmentProducer.class);
    private static final String APPOINTMENTS_TOPIC = "appointments";
    private static final String NOTIFICATIONS_TOPIC = "notifications";

    private final KafkaTemplate<String, AppointmentEvent> kafkaTemplate;

    public AppointmentProducer(KafkaTemplate<String, AppointmentEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendAppointmentEvent(AppointmentEvent event) {
        kafkaTemplate.send(APPOINTMENTS_TOPIC, String.valueOf(event.getAppointmentId()), event);
        kafkaTemplate.send(NOTIFICATIONS_TOPIC, String.valueOf(event.getOwnerId()), event);
        log.info("Kafka event sent: {} for appointment {}", event.getEventType(), event.getAppointmentId());
    }
}