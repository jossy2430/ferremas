package com.example.ms_pedido_bff.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.ms_pedido_bff.modelDto.WebpayTransactionDTO;

@FeignClient(name = "webpayClient", url = "${webpay.api.url}")
public interface WebpayClient {

    @PostMapping("/transactions")
    WebpayTransactionDTO createTransaction(@RequestBody WebpayTransactionDTO request);
}