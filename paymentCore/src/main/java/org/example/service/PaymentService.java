package org.example.service;

import org.example.model.ClientProduct;
import org.example.model.RequestDto;

import java.util.List;

public interface PaymentService {

    List<ClientProduct> getClientProducts();

    ClientProduct getClientProduct(String id);

    ClientProduct executePayment(RequestDto dto);
}
