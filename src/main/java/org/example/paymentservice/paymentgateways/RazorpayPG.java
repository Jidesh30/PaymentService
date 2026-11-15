package org.example.paymentservice.paymentgateways;

import com.razorpay.*;
import org.springframework.stereotype.Component;
import org.json.JSONObject;

@Component
public class RazorpayPG implements PaymentGateway{

    private RazorpayClient razorpayClient;

    private RazorpayPG(RazorpayClient razorpayClient) {
        this.razorpayClient = razorpayClient;
    }

    public String generatePaymentLink(String email, String phoneNumber, Long amount, String orderId) {
        JSONObject paymentLinkRequest = new JSONObject();
        paymentLinkRequest.put("amount",1000);
        paymentLinkRequest.put("currency","INR");
        paymentLinkRequest.put("accept_partial",true);
        paymentLinkRequest.put("first_min_partial_amount",100);
        paymentLinkRequest.put("expire_by",1794787200);
        paymentLinkRequest.put("reference_id","TS5189");
        paymentLinkRequest.put("description","Payment for policy no #23456");
        JSONObject customer = new JSONObject();
        customer.put("name","Jidesh");
        customer.put("contact","9487478866");
        customer.put("email","jideshsenthil98@gmail.com");
        paymentLinkRequest.put("customer",customer);
        JSONObject notify = new JSONObject();
        notify.put("sms",true);
        notify.put("email",true);
        paymentLinkRequest.put("notify",notify);
        paymentLinkRequest.put("reminder_enable",true);
        JSONObject notes = new JSONObject();
        notes.put("Notes","Payment for your amazon order");
        paymentLinkRequest.put("notes",notes);
        paymentLinkRequest.put("callback_url","https://scaler.com/");
        paymentLinkRequest.put("callback_method","get");

        PaymentLink paymentLink = null;
        try{
            paymentLink = razorpayClient.paymentLink.create(paymentLinkRequest);
        } catch (RazorpayException e) {
            throw new RuntimeException(e);
        }
        return paymentLink.get("short_url").toString();
    }
}
