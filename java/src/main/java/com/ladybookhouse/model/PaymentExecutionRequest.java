package com.ladybookhouse.model;

import java.util.Map;

public class PaymentExecutionRequest {

    private String paymentId;
    private String payerId;

    private OrderRequestDTO requestDTO;

    // Getters and setters
    public String getPaymentId() { return paymentId; }
    public void setPaymentId(String paymentId) { this.paymentId = paymentId; }

    public String getPayerId() { return payerId; }
    public void setPayerId(String payerId) { this.payerId = payerId; }


    public OrderRequestDTO getRequestDTO() {
        return requestDTO;
    }

    public void setRequestDTO(OrderRequestDTO requestDTO) {
        this.requestDTO = requestDTO;
    }
}
