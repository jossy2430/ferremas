package com.example.ms_pedido_bff;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.ms_pedido_bff.clients.WebpayClient;
import com.example.ms_pedido_bff.modelDto.WebpayTransactionDTO;
import com.example.ms_pedido_bff.service.WebpayService;

public class WebpayServiveTest {

    @Mock
    private WebpayClient webpayClient;

    @InjectMocks
    private WebpayService webpayService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        // Mock request and response
        WebpayTransactionDTO request = new WebpayTransactionDTO("token123", "https://redirect.url", 100.0, "session123", "order123");
        WebpayTransactionDTO response = new WebpayTransactionDTO("token123", "https://redirect.url", 100.0, "session123", "order123");

        when(webpayClient.createTransaction(request)).thenReturn(response);

        //llamar al metodo del service
        WebpayTransactionDTO result = webpayService.createTransaction(request);

        //Aserrtions
        assertNotNull(result);
        assertEquals("token123", result.getToken());
        assertEquals("https://redirect.url", result.getUrlRedirect());
    }

}
