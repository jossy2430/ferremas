package com.example.ms_pedido_bff.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.ms_pedido_bff.modelDto.WebpayCommitResponseDTO;
import com.example.ms_pedido_bff.modelDto.WebpayStatusResponseDTO;
import com.example.ms_pedido_bff.modelDto.WebpayTransactionDTO;

@FeignClient(name = "webpayClient", url = "${webpay.api.url}")
public interface WebpayClient {

    @PostMapping("/transactions")
    WebpayTransactionDTO createTransaction(@RequestBody WebpayTransactionDTO request);

    @PutMapping("/transactions/{token}")
    WebpayCommitResponseDTO confirmTransaction(@PathVariable("token")String token);

    @GetMapping("/transactions/{token}")
WebpayStatusResponseDTO getTransactionStatus(@PathVariable("token") String token);
}
