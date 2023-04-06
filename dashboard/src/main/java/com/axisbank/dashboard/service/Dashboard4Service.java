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
public class Dashboard4Service {

    @Autowired
    private LoanAccountRepository loanAccountRepository;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "groupcode";

    public List<Map<String, Object>> getFailureReason1CountByCboSrmId() {
        List<LoanAccount> loanAccounts = loanAccountRepository.findAll();
        Map<Integer, Integer> cboSrmIdFailureReason1CountMap = new HashMap<>();

        for (LoanAccount loanAccount : loanAccounts) {
            int cboSrmId = loanAccount.getCbo_srm_id();
            String processingStatus = loanAccount.getProcessing_status();

            if ("Failure".equalsIgnoreCase(processingStatus)) {
                cboSrmIdFailureReason1CountMap.put(cboSrmId, cboSrmIdFailureReason1CountMap.getOrDefault(cboSrmId, 0) + 1);
            }
        }

        List<Map<String, Object>> resultList = new ArrayList<>();
        for (Integer cboSrmId : cboSrmIdFailureReason1CountMap.keySet()) {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("cbo_srm_id", cboSrmId);
            resultMap.put("failure_reason_1_count", cboSrmIdFailureReason1CountMap.getOrDefault(cboSrmId, 0));
            resultList.add(resultMap);
        }

        return resultList;
    }
}