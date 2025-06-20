package com.example.ms_pedido_bff.service;

import org.springframework.stereotype.Service;

import com.example.ms_pedido_bff.clients.WebpayClient;
import com.example.ms_pedido_bff.modelDto.WebpayTransactionDTO;

@Service
public class WebpayService {

    private final WebpayClient webpayClient;

    public WebpayService(WebpayClient webpayClient) {
        this.webpayClient = webpayClient;
    }

    public WebpayTransactionDTO createTransaction(WebpayTransactionDTO request) {
        return webpayClient.createTransaction(request);
    }
}