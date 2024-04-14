package com.ladybookhouse.model;

import java.math.BigDecimal;
import java.util.List;

public class OrderRequestDTO {
        private String email;
        private List<String> inventoryCode;
        private boolean saveAddress;
        private boolean infoMail;
        private String message;
        private AddressDTO billingAddress;
        private AddressDTO shippingAddress;

        private String deliveryOption; // "USPS" or "UPS"
        private BigDecimal totalPrice; // total price including items and shipping


    public OrderRequestDTO(){

    }
    // ... getters and setters ...

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
        // Getters and setters


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getInventoryCode() {
        return inventoryCode;
    }

    public void setInventoryCode(List<String> inventoryCode) {
        this.inventoryCode = inventoryCode;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AddressDTO getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(AddressDTO billingAddress) {
        this.billingAddress = billingAddress;
    }

    public AddressDTO getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(AddressDTO shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
}
