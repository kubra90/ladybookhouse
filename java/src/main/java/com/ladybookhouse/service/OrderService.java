package com.ladybookhouse.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.ladybookhouse.model.Book;
import com.ladybookhouse.model.OrderRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {

    private final RestAobApiService restAobApiService;

    @Autowired
    public OrderService(RestAobApiService restAobApiService) {
        this.restAobApiService = restAobApiService;
    }

    public BigDecimal calculateTotalPrice(OrderRequestDTO orderRequestDTO) throws JsonProcessingException {
        BigDecimal itemPricesTotal= getItemPricesTotal(orderRequestDTO.getInventoryCode());
        BigDecimal shippingPriceTotal =calculateShippingPrice(orderRequestDTO.getInventoryCode(), orderRequestDTO.getDeliveryOption());

        return itemPricesTotal.add(shippingPriceTotal);
    }


    public BigDecimal getItemPricesTotal(List<String> inventoryCodes) throws JsonProcessingException {
        BigDecimal total = BigDecimal.ZERO;

        for (String inventoryCode : inventoryCodes) {
            Book book = restAobApiService.getBookInfo(inventoryCode);
            // Add the price of the book to the total
            total = total.add(book.getPrice());
        }

        return total.setScale(2, BigDecimal.ROUND_HALF_EVEN); // Ensures two decimal places
    }

    // ... other service methods ...
    public BigDecimal calculateShippingPrice(List<String> inventoryCodes, String deliveryOption) throws JsonProcessingException {
        int totalWeight=0;
        for (String inventoryCode : inventoryCodes) {
            Book book = restAobApiService.getBookInfo(inventoryCode);
            // Add the price of the book to the total
            totalWeight += book.getWeight();
        }

        return calculateShippingCostByWeightAndOption(totalWeight, deliveryOption);
    }

    private BigDecimal calculateShippingCostByWeightAndOption(int weight, String delivery) {
        BigDecimal totalShippingCost = BigDecimal.ZERO;

        BigDecimal shippingCostByWeight = BigDecimal.ZERO;

        if (weight <= 16) {
            shippingCostByWeight = new BigDecimal("4.50");
        } else if (weight > 16 && weight < 33) {
            shippingCostByWeight = new BigDecimal("5.20");
        } else if (weight > 33 && weight < 49) {
            shippingCostByWeight = new BigDecimal("5.90");
        } else if (weight > 48 && weight < 65) {
            shippingCostByWeight = new BigDecimal("6.60");
        } else if (weight > 64 && weight < 81) {
            shippingCostByWeight = new BigDecimal("7.30");
        } else if (weight > 80 && weight < 97) {
            shippingCostByWeight = new BigDecimal("8.00");
        } else {
            shippingCostByWeight = new BigDecimal("8.70");
        }

        if (delivery.equals("UPS")) {
            totalShippingCost = shippingCostByWeight.add(new BigDecimal("9.90"));
        } else {
            totalShippingCost = shippingCostByWeight;
        }
        return totalShippingCost;
    }
}

