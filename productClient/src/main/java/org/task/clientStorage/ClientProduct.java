package org.task.clientStorage;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ClientProduct {
    public int id;
    public String accountNumber;
    public BigDecimal balance;
    public ProductType productType;
    public Long userId;
}
