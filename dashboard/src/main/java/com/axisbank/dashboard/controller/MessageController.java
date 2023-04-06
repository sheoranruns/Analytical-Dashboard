package com.axisbank.dashboard.controller;

import com.axisbank.dashboard.model.LoanAccount;
import com.axisbank.dashboard.repository.LoanAccountRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class MessageController {

    private KafkaTemplate<String, String> kafkaTemplate;
    private LoanAccountRepository loanAccountRepository;

    public MessageController(KafkaTemplate<String, String> kafkaTemplate, LoanAccountRepository loanAccountRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.loanAccountRepository = loanAccountRepository;
    }

    @GetMapping("/all")
    public ResponseEntity<List<LoanAccount>> getAllLoanAccounts() {
        List<LoanAccount> loanAccounts = loanAccountRepository.findAll();
        kafkaTemplate.send("groupcode", loanAccounts.toString());
        return ResponseEntity.ok(loanAccounts);
    }

}
