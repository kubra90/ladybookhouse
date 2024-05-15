package com.ladybookhouse.model;

public class PaymentExecutionRequest {

    private String paymentId;
    private String payerId;

    // Getters and setters
    public String getPaymentId() { return paymentId; }
    public void setPaymentId(String paymentId) { this.paymentId = paymentId; }

    public String getPayerId() { return payerId; }
    public void setPayerId(String payerId) { this.payerId = payerId; }
}
