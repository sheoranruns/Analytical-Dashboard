package com.axisbank.dashboard.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    @KafkaListener(topics="groupcode", groupId = "groupId")
    void listener(String data) {
    }
}
