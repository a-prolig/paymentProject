package org.task.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.task.entity.ClientProduct;
import org.task.repository.ClientProductRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class ClientProductServiceImpl implements ClientProductService{

    private final ClientProductRepository repository;

    public ClientProductServiceImpl(ClientProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public ClientProduct findOne(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException(String.format("Продукт клиента id=%d не найден", id)));
    }

    @Override
    public List<ClientProduct> findAll() {
        return repository.findAll();
    }

    @Override
    public ClientProduct save(ClientProduct clientProduct) {
        ClientProduct entity;
        try {
            entity = repository.saveAndFlush(clientProduct);

        } catch (DataIntegrityViolationException e) {
            log.error(e.getMessage());
            throw e;
        }
        return entity;
    }

    @Override
    public void delete(Long id) {
        ClientProduct clientProduct = findOne(id);
        repository.delete(clientProduct);
    }

    @Override
    public List<ClientProduct> getProductByUserId(long userId) {
        return repository.findProductByUserId(userId);
    }

    @Override
    public ClientProduct updateProductBalance(long productId, BigDecimal amount) {
        ClientProduct product = findOne(productId);
        BigDecimal balance = product.getBalance();
        String accountNumber = product.getAccountNumber();
        if (Objects.isNull(balance) || StringUtils.isEmpty(balance) || balance.compareTo(amount) < 0) {
            throw new RuntimeException("Product " + accountNumber + " has less balance then current amount");
        }
        BigDecimal newBalance = balance.subtract(amount);
        product.setBalance(newBalance);
        return save(product);
    }

    }
