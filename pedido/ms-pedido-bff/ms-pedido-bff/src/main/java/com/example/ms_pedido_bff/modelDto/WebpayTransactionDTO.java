package com.example.ms_pedido_bff.modelDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WebpayTransactionDTO {
    private String token;
    private String urlRedirect;
    private Double amount;
    private String sessionId;
    private String buyOrder;
}