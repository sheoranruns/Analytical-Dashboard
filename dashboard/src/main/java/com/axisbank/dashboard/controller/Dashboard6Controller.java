package com.axisbank.dashboard.controller;

import com.axisbank.dashboard.service.Dashboard6Service;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dashboard6")
@CrossOrigin("http://localhost:3000")
public class Dashboard6Controller {

    private KafkaTemplate<String, String> kafkaTemplate;

    private Dashboard6Service dashboard6Service;

    public Dashboard6Controller(KafkaTemplate<String, String> kafkaTemplate, Dashboard6Service dashboard6Service) {
        this.kafkaTemplate = kafkaTemplate;
        this.dashboard6Service = dashboard6Service;
    }

    @GetMapping("/good_customers_by_cbo_srm_id")
    public ResponseEntity<List<Map<String, Object>>> getGoodCustomersByCboSrmId() {
        List<Map<String, Object>> resultList = dashboard6Service.getGoodCustomersByCboSrmId();

        for (Map<String, Object> resultMap : resultList) {
            kafkaTemplate.send("groupcode", resultMap.toString());
        }

        return ResponseEntity.ok().body(resultList);
    }

}
