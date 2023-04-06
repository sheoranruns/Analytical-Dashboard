package com.axisbank.dashboard.controller;

import com.axisbank.dashboard.service.Dashboard3Service;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dashboard3")
@CrossOrigin("http://localhost:3000")
public class Dashboard3Controller {

    private KafkaTemplate<String, String> kafkaTemplate;
    private Dashboard3Service dashboard3Service;

    public Dashboard3Controller(KafkaTemplate<String, String> kafkaTemplate, Dashboard3Service dashboard3Service) {
        this.kafkaTemplate = kafkaTemplate;
        this.dashboard3Service = dashboard3Service;
    }

    @GetMapping("/failure_reason_count_by_sol_id")
    public ResponseEntity<List<Map<String, Object>>> getFailureReasonCountBySolId() {
        List<Map<String, Object>> resultList = dashboard3Service.getFailureReasonCountBySolId();

        for (Map<String, Object> resultMap : resultList) {
            kafkaTemplate.send("groupcode", resultMap.toString());
        }

        return ResponseEntity.ok().body(resultList);
    }

}