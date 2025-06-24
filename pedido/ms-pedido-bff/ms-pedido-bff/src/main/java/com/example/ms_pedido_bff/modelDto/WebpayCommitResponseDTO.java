package com.example.ms_pedido_bff.modelDto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WebpayCommitResponseDTO {
    private String vci;
    private Double amount;
    private String status;
    private String buyOrder;
    private String sessionId;
    private String cardDetail;
    private String accountingDate;
    private LocalDateTime transactionDate;
    private String authorizationCode;
    private String paymentTypeCode;
    private Integer responseCode;
    private Integer installmentsAmount;
    private Integer installmentsNumber;
    private Double balance;

    
}
