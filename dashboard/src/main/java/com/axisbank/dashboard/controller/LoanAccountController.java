package com.axisbank.dashboard.controller;

import com.axisbank.dashboard.model.LoanAccount;
import com.axisbank.dashboard.service.LoanAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loan-accounts")
public class LoanAccountController {

    @Autowired
    private LoanAccountService loanAccountService;

    @PatchMapping("/{srNo}")
    public ResponseEntity<?> updateLoanAccount(@PathVariable("srNo") int srNo,
                                               @RequestBody LoanAccount loanAccount) {
        LoanAccount updatedLoanAccount = loanAccountService.updateLoanAccount(srNo, loanAccount);
        return new ResponseEntity<>(updatedLoanAccount, HttpStatus.OK);
    }
}
