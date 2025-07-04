package com.example.ms_pedido_bff.controller;

import org.springframework.web.bind.annotation.*;

import com.example.ms_pedido_bff.modelDto.WebpayCommitResponseDTO;
import com.example.ms_pedido_bff.modelDto.WebpayStatusResponseDTO;
import com.example.ms_pedido_bff.modelDto.WebpayTransactionDTO;
import com.example.ms_pedido_bff.service.WebpayService;

@RestController
@RequestMapping("/api/webpay")
public class WebpayController {

    private final WebpayService webpayService;

    public WebpayController(WebpayService webpayService) {
        this.webpayService = webpayService;
    }

    @PostMapping("/transactions")
    public WebpayTransactionDTO createTransaction(@RequestBody WebpayTransactionDTO request) {
        return webpayService.createTransaction(request);
    }

    @PutMapping("/transactions/{token}/commit")
    public WebpayCommitResponseDTO confirmTransaction(@PathVariable String token) {
        return webpayService.confirmTransaction(token);
    }
    
    @GetMapping("/transactions/{token}/status")
    public WebpayStatusResponseDTO getTransactionStatus(@PathVariable String token) {
        return webpayService.getTransactionStatus(token);
    }

    
}
