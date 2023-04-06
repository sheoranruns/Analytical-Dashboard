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
public class Dashboard1Service {

    @Autowired
    private LoanAccountRepository loanAccountRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "groupcode";

    public List<Map<String, Object>> getCboSrmTotalInterest() {
        List<LoanAccount> loanAccountList = loanAccountRepository.findAll();
        Map<Integer, Double> cboSrmTotalInterestMap = new HashMap<>();

        for (LoanAccount loanAccount : loanAccountList) {
            int cboSrmId = loanAccount.getCbo_srm_id();
            Double normalInterest = loanAccount.getNormal_interest();
            Double penalInterest = loanAccount.getPenal_interest();

            double totalInterest = 0.0;
            if (penalInterest != null) {
                totalInterest = normalInterest + penalInterest;
            } else {
                totalInterest = normalInterest;
            }
            cboSrmTotalInterestMap.put(cboSrmId, cboSrmTotalInterestMap.getOrDefault(cboSrmId, 0.0) + totalInterest);
        }

        // Convert the map into a list of objects with cbo_srm_id and total_interest fields
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (Map.Entry<Integer, Double> entry : cboSrmTotalInterestMap.entrySet()) {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("cbo_srm_id", entry.getKey());
            resultMap.put("total_interest", entry.getValue());
            resultList.add(resultMap);
        }

        return resultList;
    }
}


