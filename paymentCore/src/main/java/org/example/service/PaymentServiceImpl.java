package org.example.service;

import org.example.model.ClientProduct;
import org.example.model.RequestDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService{
    private final RestTemplate restTemplate;

    public PaymentServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<ClientProduct> getClientProducts() {
        return restTemplate.getForObject("http://127.0.0.1:8081/clientProjects/getAllProducts", List.class);
    }

    @Override
    public ClientProduct getClientProduct(String id) {
        return restTemplate.getForObject(String.format("http://127.0.0.1:8081/clientProjects/getProduct?id=%s", id), ClientProduct.class);
    }

    @Override
    public ClientProduct executePayment(RequestDto dto) {
        return restTemplate.postForObject("http://localhost:8081/clientProjects/updateProductBalance", dto, ClientProduct.class);
    }
}
