package com.axisbank.dashboard.controller;

import com.axisbank.dashboard.service.Dashboard1Service;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dashboard1")
@CrossOrigin("http://localhost:3000")
public class Dashboard1Controller {

    private KafkaTemplate<String, String> kafkaTemplate;
    private Dashboard1Service dashboard1Service;

    public Dashboard1Controller(KafkaTemplate<String, String> kafkaTemplate, Dashboard1Service dashboard1Service) {
        this.kafkaTemplate = kafkaTemplate;
        this.dashboard1Service = dashboard1Service;
    }

    @GetMapping("/cbo_srm_total_interest")
    public ResponseEntity<List<Map<String, Object>>> getCboSrmTotalInterest() {
        List<Map<String, Object>> resultList = dashboard1Service.getCboSrmTotalInterest();

        for (Map<String, Object> resultMap : resultList) {
            kafkaTemplate.send("groupcode", resultMap.toString());
        }

        return ResponseEntity.ok().body(resultList);
    }

}

