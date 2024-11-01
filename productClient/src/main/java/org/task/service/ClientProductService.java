package org.task.service;

import org.task.entity.ClientProduct;

import java.math.BigDecimal;
import java.util.List;

public interface ClientProductService {
    List<ClientProduct> findAll();
    ClientProduct findOne(Long id);
    ClientProduct save(ClientProduct clientProduct);
    void delete(Long id);
    List<ClientProduct> getProductByUserId(long userId);
    ClientProduct updateProductBalance(long productId, BigDecimal amount);
}
