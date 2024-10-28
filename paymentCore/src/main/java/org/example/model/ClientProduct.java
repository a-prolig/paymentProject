package org.example.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ClientProduct {
    public int id;
    public String accountNumber;
    public BigDecimal balance;
    public String productType;
    public Long userId;
}
