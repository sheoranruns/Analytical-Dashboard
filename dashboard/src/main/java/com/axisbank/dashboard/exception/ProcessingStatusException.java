package com.axisbank.dashboard.exception;
public class ProcessingStatusException extends RuntimeException {
    private int failureReason;

    public ProcessingStatusException(String message, int failureReason) {
        super(message);
        this.failureReason = failureReason;
    }
    public int getFailureReason() {
        return failureReason;
    }

    public void setFailureReason(int failureReason) {
        this.failureReason = failureReason;
    }
}
