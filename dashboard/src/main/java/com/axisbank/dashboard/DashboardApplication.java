package com.axisbank.dashboard;

import com.axisbank.dashboard.model.LoanAccount;
import com.axisbank.dashboard.repository.LoanAccountRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.List;


@SpringBootApplication
@EnableMongoRepositories("com.axisbank.dashboard.repository")
public class DashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(DashboardApplication.class, args);
	}

	@Autowired
	private LoanAccountRepository loanAccountRepository;
	@Bean
	CommandLineRunner commandLineRunner(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
		return args -> {
			List<LoanAccount> loanAccounts = loanAccountRepository.findAll();
			String loanAccountsJson = objectMapper.writeValueAsString(loanAccounts);
			kafkaTemplate.send("groupcode", loanAccountsJson);
		};
	}



}
