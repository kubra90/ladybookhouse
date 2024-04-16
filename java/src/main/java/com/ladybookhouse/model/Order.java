package com.ladybookhouse.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private int orderId;
    private String email;
//    private String phoneNumber;

    private int billingId;

    private int shippingId;
    private List<String> inventoryCode;

    private String deliveryOption;

    private BigDecimal totalPrice;

    private String message;

    private boolean saveAddress;

    private boolean infoMail;

    private LocalDateTime orderDateTime;

    public Order() {
    }

    public Order(int orderId, String email, int billingId, int shippingId, List<String> inventoryCode, String deliveryOption,  BigDecimal totalPrice, String message, boolean saveAddress, boolean infoMail, LocalDateTime orderDateTime) {
        this.orderId = orderId;
        this.email = email;
        this.billingId = billingId;
        this.shippingId = shippingId;
        this.inventoryCode = inventoryCode;
        this.deliveryOption =deliveryOption;
        this.totalPrice =totalPrice;
        this.message = message;
        this.saveAddress = saveAddress;
        this.infoMail = infoMail;
        this.orderDateTime = orderDateTime;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getEmail() {
        return email;
    }

    public String getDeliveryOption() {
        return deliveryOption;
    }

    public void setDeliveryOption(String deliveryOption) {
        this.deliveryOption = deliveryOption;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getBillingId() {
        return billingId;
    }

    public void setBillingId(int billingId) {
        this.billingId = billingId;
    }

    public int getShippingId() {
        return shippingId;
    }

    public void setShippingId(int shippingId) {
        this.shippingId = shippingId;
    }

    public List<String> getInventoryCode() {
        return inventoryCode;
    }

    public void setInventoryCode(List<String> inventoryCode) {
        this.inventoryCode = inventoryCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSaveAddress() {
        return saveAddress;
    }

    public void setSaveAddress(boolean saveAddress) {
        this.saveAddress = saveAddress;
    }

    public boolean isInfoMail() {
        return infoMail;
    }

    public void setInfoMail(boolean infoMail) {
        this.infoMail = infoMail;
    }

    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(LocalDateTime orderDateTime) {
        this.orderDateTime = orderDateTime;
    }
}
