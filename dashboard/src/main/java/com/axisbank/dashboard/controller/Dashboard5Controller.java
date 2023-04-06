package com.axisbank.dashboard.controller;

import com.axisbank.dashboard.service.Dashboard5Service;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dashboard5")
@CrossOrigin("http://localhost:3000")
public class Dashboard5Controller {

    private KafkaTemplate<String, String> kafkaTemplate;

    private Dashboard5Service dashboard5Service;

    public Dashboard5Controller(KafkaTemplate<String, String> kafkaTemplate, Dashboard5Service dashboard5Service) {
        this.kafkaTemplate = kafkaTemplate;
        this.dashboard5Service = dashboard5Service;
    }

    @GetMapping("/failure_reason_4_count_by_cbo_srm_id")
    public ResponseEntity<List<Map<String, Object>>> getFailureReason4CountByCboSrmId() {
        List<Map<String, Object>> resultList = dashboard5Service.getFailureReason4CountByCboSrmId();

        for (Map<String, Object> resultMap : resultList) {
            kafkaTemplate.send("groupcode", resultMap.toString());
        }


        return ResponseEntity.ok().body(resultList);
    }
}
