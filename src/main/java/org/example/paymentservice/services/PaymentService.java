package org.example.paymentservice.services;

import org.example.paymentservice.paymentgateways.PaymentGateway;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final PaymentGateway paymentGateway;

    public PaymentService(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    public String initiatePayment(String email, String phoneNumber, Long amount, String orderId) {
        /*
        1. Call order service
        2. Get Order details
        3. verfiy amount
        4. Call PG to generate payment link
        5. Return payment link
         */
        return paymentGateway.generatePaymentLink(email, phoneNumber, amount, orderId);
    }
}
