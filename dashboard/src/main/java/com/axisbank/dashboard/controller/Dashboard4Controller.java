package com.axisbank.dashboard.controller;

import com.axisbank.dashboard.service.Dashboard4Service;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dashboard4")
@CrossOrigin("http://localhost:3000")
public class Dashboard4Controller {

    private KafkaTemplate<String, String> kafkaTemplate;
    private Dashboard4Service dashboard4Service;

    public Dashboard4Controller(KafkaTemplate<String, String> kafkaTemplate, Dashboard4Service dashboard4Service) {
        this.kafkaTemplate = kafkaTemplate;
        this.dashboard4Service = dashboard4Service;
    }

    @GetMapping("/failure_reason_1_count_by_cbo_srm_id")
    public ResponseEntity<List<Map<String, Object>>> getFailureReason1CountByCboSrmId() {
        List<Map<String, Object>> resultList = dashboard4Service.getFailureReason1CountByCboSrmId();

        for (Map<String, Object> resultMap : resultList) {
            kafkaTemplate.send("groupcode", resultMap.toString());
        }

        return ResponseEntity.ok().body(resultList);
    }

}