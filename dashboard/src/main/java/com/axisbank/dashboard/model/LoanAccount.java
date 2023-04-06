package com.axisbank.dashboard.model;


import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document(collection = "loan_accounts")
public class LoanAccount {
    private int sr_no;

    private int sol_id;

    private String acct_name;

    private String schm_code;

    private Date principal_payment_due_date;

    private double principal_payment_amount;

    private Date interest_payment_due_date;

    private double normal_interest;

    private String interest_calc_status;

    private Double penal_interest;

    private String penal_calc_status;

    private String processing_status;

    private int primary_manager_id;

    private int secondary_manager_id;

    private int tertiary_manager_id;

    private int cbo_srm_id;

    private int credit_admin_manager_id;


    // getters and setters


    public int getSr_no() {
        return sr_no;
    }

    public void setSr_no(int sr_no) {
        this.sr_no = sr_no;
    }

    public int getSol_id() {
        return sol_id;
    }

    public void setSol_id(int sol_id) {
        this.sol_id = sol_id;
    }

    public String getAcct_name() {
        return acct_name;
    }

    public void setAcct_name(String acct_name) {
        this.acct_name = acct_name;
    }

    public String getSchm_code() {
        return schm_code;
    }

    public void setSchm_code(String schm_code) {
        this.schm_code = schm_code;
    }

    public Date getPrincipal_payment_due_date() {
        return principal_payment_due_date;
    }

    public void setPrincipal_payment_due_date(Date principal_payment_due_date) {
        this.principal_payment_due_date = principal_payment_due_date;
    }

    public double getPrincipal_payment_amount() {
        return principal_payment_amount;
    }

    public void setPrincipal_payment_amount(double principal_payment_amount) {
        this.principal_payment_amount = principal_payment_amount;
    }

    public Date getInterest_payment_due_date() {
        return interest_payment_due_date;
    }

    public void setInterest_payment_due_date(Date interest_payment_due_date) {
        this.interest_payment_due_date = interest_payment_due_date;
    }

    public double getNormal_interest() {
        return normal_interest;
    }

    public void setNormal_interest(double normal_interest) {
        this.normal_interest = normal_interest;
    }

    public String getInterest_calc_status() {
        return interest_calc_status;
    }

    public void setInterest_calc_status(String interest_calc_status) {
        this.interest_calc_status = interest_calc_status;
    }

    public Double getPenal_interest() {
        return penal_interest;
    }

    public void setPenal_interest(Double penal_interest) {
        this.penal_interest = penal_interest;
    }

    public String getPenal_calc_status() {
        return penal_calc_status;
    }

    public void setPenal_calc_status(String penal_calc_status) {
        this.penal_calc_status = penal_calc_status;
    }

    public String getProcessing_status() {
        return processing_status;
    }

    public void setProcessing_status(String processing_status) {
        this.processing_status = processing_status;
    }

    public int getPrimary_manager_id() {
        return primary_manager_id;
    }

    public void setPrimary_manager_id(int primary_manager_id) {
        this.primary_manager_id = primary_manager_id;
    }

    public int getSecondary_manager_id() {
        return secondary_manager_id;
    }

    public void setSecondary_manager_id(int secondary_manager_id) {
        this.secondary_manager_id = secondary_manager_id;
    }

    public int getTertiary_manager_id() {
        return tertiary_manager_id;
    }

    public void setTertiary_manager_id(int tertiary_manager_id) {
        this.tertiary_manager_id = tertiary_manager_id;
    }

    public int getCbo_srm_id() {
        return cbo_srm_id;
    }

    public void setCbo_srm_id(int cbo_srm_id) {
        this.cbo_srm_id = cbo_srm_id;
    }

    public int getCredit_admin_manager_id() {
        return credit_admin_manager_id;
    }

    public void setCredit_admin_manager_id(int credit_admin_manager_id) {
        this.credit_admin_manager_id = credit_admin_manager_id;
    }
}
