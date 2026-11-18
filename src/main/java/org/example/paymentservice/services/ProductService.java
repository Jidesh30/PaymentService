package org.example.paymentservice.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service

public class ProductService {

    private final RestTemplate restTemplate;

    public ProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getProductDetails(String productId) {
        return restTemplate.getForObject("http://PRODUCTSERVICE/products/" + productId, String.class);
    }
}
