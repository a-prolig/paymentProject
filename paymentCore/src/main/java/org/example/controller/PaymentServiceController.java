package org.example.controller;

import org.example.model.ClientProduct;
import org.example.model.RequestDto;
import org.example.service.PaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/paymentService")
public class PaymentServiceController {

    private final PaymentService paymentService;

    public PaymentServiceController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/getClientProducts")
    public List<ClientProduct> getClientProducts() {
      return paymentService.getClientProducts();
    }

    @GetMapping("/getClientProduct/{id}")
    public ClientProduct getClientProduct(@PathVariable String id) {
        return paymentService.getClientProduct(id);
    }

    @PostMapping("/executePayment")
    public ClientProduct executePayment(@RequestBody RequestDto dto) {
        return paymentService.executePayment(dto);
    }

}
