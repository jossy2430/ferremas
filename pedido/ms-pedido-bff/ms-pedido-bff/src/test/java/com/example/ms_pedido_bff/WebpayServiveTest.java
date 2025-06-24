package com.example.ms_pedido_bff;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.ms_pedido_bff.clients.WebpayClient;
import com.example.ms_pedido_bff.modelDto.WebpayTransactionDTO;
import com.example.ms_pedido_bff.service.WebpayService;

public class WebpayServiceTest {  // Corrección: era "WebpayServiveTest"

    @Mock
    private WebpayClient webpayClient;

    @InjectMocks
    private WebpayService webpayService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTransaction() {
        // Mock request and response
        WebpayTransactionDTO request = new WebpayTransactionDTO("token123", "https://redirect.url", 100.0, "session123", "order123", "https://return.url");
        WebpayTransactionDTO response = new WebpayTransactionDTO("token123", "https://redirect.url", 100.0, "session123", "order123", "https://return.url");

        // Mock del cliente
        when(webpayClient.createTransaction(request)).thenReturn(response);

        // Llamar al método del service
        WebpayTransactionDTO result = webpayService.createTransaction(request);

        // Assertions
        assertNotNull(result);
        assertEquals("token123", result.getToken());
        assertEquals("https://redirect.url", result.getUrlRedirect());
        assertEquals(100.0, result.getAmount());
        assertEquals("session123", result.getSessionId());
        assertEquals("order123", result.getBuyOrder());
    }
}
