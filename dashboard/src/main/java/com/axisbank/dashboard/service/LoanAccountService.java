package com.axisbank.dashboard.service;

import com.axisbank.dashboard.model.LoanAccount;
import com.axisbank.dashboard.repository.LoanAccountRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoanAccountService {

    @Autowired
    private LoanAccountRepository loanAccountRepository;

    public LoanAccount updateLoanAccount(int srNo, LoanAccount updatedLoanAccount) {
        Optional<LoanAccount> existingLoanAccount = loanAccountRepository.findById(String.valueOf(srNo));

        if (existingLoanAccount.isPresent()) {
            LoanAccount loanAccount = existingLoanAccount.get();
            BeanUtils.copyProperties(updatedLoanAccount, loanAccount, "sr_no");
            loanAccountRepository.save(loanAccount);
            return loanAccount;
        }

        return null;
    }
}
