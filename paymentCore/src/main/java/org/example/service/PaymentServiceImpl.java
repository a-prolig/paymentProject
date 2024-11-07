package org.example.service;

import org.example.model.ClientProduct;
import org.example.model.RequestDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService{
    private final String CLIENT_PROJECTS_API_PATH = "http://127.0.0.1:8081/clientProjects";
    private final RestTemplate restTemplate;

    public PaymentServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<ClientProduct> getClientProducts() {
        return restTemplate.getForObject(String.format("%s/getAllProducts", CLIENT_PROJECTS_API_PATH), List.class);
    }

    @Override
    public ClientProduct getClientProduct(String id) {
        return restTemplate.getForObject(String.format("%s/getProduct?id=%s", CLIENT_PROJECTS_API_PATH, id), ClientProduct.class);
    }

    @Override
    public ClientProduct executePayment(RequestDto dto) {
        return restTemplate.postForObject(String.format("%s/updateProductBalance", CLIENT_PROJECTS_API_PATH), dto, ClientProduct.class);
    }
}
