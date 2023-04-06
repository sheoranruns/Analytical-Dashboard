package com.axisbank.dashboard.service;

import com.axisbank.dashboard.model.LoanAccount;
import com.axisbank.dashboard.repository.LoanAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Dashboard3Service {

    @Autowired
    private LoanAccountRepository loanAccountRepository;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "groupcode";

    public List<Map<String, Object>> getFailureReasonCountBySolId() {
        List<LoanAccount> loanAccounts = loanAccountRepository.findAll();
        Map<Integer, Integer> solIdFailureReason1CountMap = new HashMap<>();
        Map<Integer, Integer> solIdFailureReason4CountMap = new HashMap<>();

        for (LoanAccount loanAccount : loanAccounts) {
            int solId = loanAccount.getSol_id();
            String processingStatus = loanAccount.getProcessing_status();

            if ("Failure".equalsIgnoreCase(processingStatus)) {
                solIdFailureReason1CountMap.put(solId, solIdFailureReason1CountMap.getOrDefault(solId, 0) + 1);
            } else if ("Cust_Email_Is_Blank".equalsIgnoreCase(processingStatus) || processingStatus.equals("Invalid Customer Id")) {
                solIdFailureReason4CountMap.put(solId, solIdFailureReason4CountMap.getOrDefault(solId, 0) + 1);
            }
        }

        List<Map<String, Object>> resultList = new ArrayList<>();
        for (Integer solId : solIdFailureReason1CountMap.keySet()) {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("sol_id", solId);
            resultMap.put("failure_reason_1_count", solIdFailureReason1CountMap.getOrDefault(solId, 0));
            resultMap.put("failure_reason_4_count", solIdFailureReason4CountMap.getOrDefault(solId, 0));
            resultList.add(resultMap);
        }
        for (Integer solId : solIdFailureReason4CountMap.keySet()) {
            if (!solIdFailureReason1CountMap.containsKey(solId)) {
                Map<String, Object> resultMap = new HashMap<>();
                resultMap.put("sol_id", solId);
                resultMap.put("failure_reason_1_count", 0);
                resultMap.put("failure_reason_4_count", solIdFailureReason4CountMap.getOrDefault(solId, 0));
                resultList.add(resultMap);
            }
        }

        return resultList;
    }
}
