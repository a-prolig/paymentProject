package org.task.clientStorage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class ClientProductService {
    private final ClientProductDao clientProductDao;
    public ClientProductService(ClientProductDao clientProductDao){
        this.clientProductDao = clientProductDao;
    }

    public ClientProduct getProduct(int id) {
        return clientProductDao.getProduct(id);
    }

    public List<ClientProduct> findAll() {
        return clientProductDao.getAll();
    }

    public List<ClientProduct> findProductByUserId(long userId) {
        return clientProductDao.getProductByUserId(userId);
    }

    public void saveProduct(List<ClientProduct> clientProducts) {
        clientProductDao.save(clientProducts);
    }

    public ClientProduct updateProductBalance(int productId, BigDecimal amount) {
        ClientProduct product = getProduct(productId);
        BigDecimal balance = product.getBalance();
        String accountNumber = product.getAccountNumber();
        if (Objects.isNull(balance) || StringUtils.isEmpty(balance) || balance.compareTo(amount) < 0) {
            throw new RuntimeException("Product " + accountNumber + "has less balance then current amount");
        }
        BigDecimal newBalance = balance.subtract(amount);
        product.setBalance(newBalance);
        return clientProductDao.update(product);
    }
}
