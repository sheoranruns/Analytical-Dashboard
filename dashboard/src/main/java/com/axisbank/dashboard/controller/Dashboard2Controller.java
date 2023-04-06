package com.axisbank.dashboard.controller;

import com.axisbank.dashboard.service.Dashboard2Service;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dashboard2")
@CrossOrigin("http://localhost:3000")
public class Dashboard2Controller {

    private KafkaTemplate<String, String> kafkaTemplate;

    private Dashboard2Service dashboard2Service;

    public Dashboard2Controller(KafkaTemplate<String, String> kafkaTemplate, Dashboard2Service dashboard2Service) {
        this.kafkaTemplate = kafkaTemplate;
        this.dashboard2Service = dashboard2Service;
    }

    @GetMapping("/total_interest_by_sol_id")
    public ResponseEntity<List<Map<String, Object>>> getTotalInterestBySolId() {
        List<Map<String, Object>> resultList = dashboard2Service.getTotalInterestBySolId();

        for (Map<String, Object> resultMap : resultList) {
            kafkaTemplate.send("groupcode", resultMap.toString());
        }

        return ResponseEntity.ok().body(resultList);
    }

}
