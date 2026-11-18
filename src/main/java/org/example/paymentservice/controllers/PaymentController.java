package org.example.paymentservice.controllers;

import org.example.paymentservice.dtos.InitiatePaymentDto;
import org.example.paymentservice.services.PaymentService;
import org.example.paymentservice.services.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    private final PaymentService paymentService;

    private final ProductService productService;

    public PaymentController(PaymentService paymentService, ProductService productService) {
        this.paymentService = paymentService;
        this.productService = productService;
    }

    @PostMapping
    public String initiatePayment(@RequestBody InitiatePaymentDto initiatePaymentDto) {
        return paymentService.initiatePayment(initiatePaymentDto.getEmail(),
                initiatePaymentDto.getPhoneNumber(),
                initiatePaymentDto.getAmount(),
                initiatePaymentDto.getOrderId());
    }

    @PostMapping("/webhook")
    public String listenToWebhook(@RequestBody String webhookEvent) {
        System.out.println(webhookEvent);
        return "Webhook received successfully";
    }

    @GetMapping("/product/{id}")
    public String getProductDetails(@PathVariable String id) {
        return productService.getProductDetails(id);
    }
}
