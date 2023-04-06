package com.axisbank.dashboard.repository;

import com.axisbank.dashboard.model.LoanAccount;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoanAccountRepository extends MongoRepository<LoanAccount, String> {

}
