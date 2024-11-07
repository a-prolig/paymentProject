package org.task.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.task.entity.ClientProduct;
import org.task.service.ClientProductService;

import java.util.List;

@RestController
@RequestMapping("/clientProjects")
public class ClientProductController {
    private final ClientProductService clientProductService;

    public ClientProductController(ClientProductService clientProductService) {
        this.clientProductService = clientProductService;
    }

    @GetMapping("/getAllProducts")
    public List<ClientProduct> getAllProducts() {
        return clientProductService.findAll();
    }

    @GetMapping("/getProduct")
    public ClientProduct getProduct(@RequestParam long id) {
        return clientProductService.findOne(id);
    }

    @GetMapping("/getUserProduct")
    public List<ClientProduct> getUserProduct(@RequestParam long userId) {
        return clientProductService.getProductByUserId(userId);
    }

    @PostMapping("/saveProduct")
    public void saveProduct(@RequestBody ClientProduct clientProduct) {
        clientProductService.save(clientProduct);
    }

    @PostMapping("/updateProductBalance")
    public ClientProduct updateProductBalance(@RequestBody ClientProduct clientProduct) {
        return clientProductService.updateProductBalance(clientProduct.getId(), clientProduct.getBalance());
    }
}
