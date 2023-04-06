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
public class Dashboard6Service {

    @Autowired
    private LoanAccountRepository loanAccountRepository;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "groupcode";

    public List<Map<String, Object>> getGoodCustomersByCboSrmId() {
        List<LoanAccount> loanAccounts = loanAccountRepository.findAll();
        Map<Integer, Integer> solIdFailureReason2CountMap = new HashMap<>();
        Map<Integer, Integer> solIdFailureReason3CountMap = new HashMap<>();

        for (LoanAccount loanAccount : loanAccounts) {
            int cboSrmId = loanAccount.getCbo_srm_id();
            String processingStatus = loanAccount.getProcessing_status();

            if ("No_Interest_Payment_Due".equalsIgnoreCase(processingStatus)) {
                solIdFailureReason2CountMap.put(cboSrmId, solIdFailureReason2CountMap.getOrDefault(cboSrmId, 0) + 1);
            } else if ("Normal_Interest_Is_Zero".equalsIgnoreCase(processingStatus) || processingStatus.equals("Normal_Interest_Is_Null")) {
                solIdFailureReason3CountMap.put(cboSrmId, solIdFailureReason3CountMap.getOrDefault(cboSrmId, 0) + 1);
            }
        }

        List<Map<String, Object>> resultList = new ArrayList<>();
        for (Integer solId : solIdFailureReason2CountMap.keySet()) {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("sol_id", solId);
            resultMap.put("failure_reason_2_count", solIdFailureReason2CountMap.getOrDefault(solId, 0));
            resultMap.put("failure_reason_3_count", solIdFailureReason3CountMap.getOrDefault(solId, 0));
            resultList.add(resultMap);
        }
        for (Integer solId : solIdFailureReason3CountMap.keySet()) {
            if (!solIdFailureReason2CountMap.containsKey(solId)) {
                Map<String, Object> resultMap = new HashMap<>();
                resultMap.put("sol_id", solId);
                resultMap.put("failure_reason_2_count", 0);
                resultMap.put("failure_reason_3_count", solIdFailureReason3CountMap.getOrDefault(solId, 0));
                resultList.add(resultMap);
            }
        }

        return resultList;
    }
}
