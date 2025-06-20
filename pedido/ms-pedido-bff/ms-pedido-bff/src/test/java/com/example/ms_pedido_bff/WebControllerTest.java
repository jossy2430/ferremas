package com.example.ms_pedido_bff;

import com.example.ms_pedido_bff.controller.WebpayController;
import com.example.ms_pedido_bff.modelDto.WebpayTransactionDTO;
import com.example.ms_pedido_bff.service.WebpayService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WebpayController.class)
public class WebControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WebpayService webpayService;

    @Test
    void testCreateTransaction() throws Exception {
        // Mock request and response
        WebpayTransactionDTO request = new WebpayTransactionDTO("token123", "https://redirect.url", 100.0, "session123", "order123");
        WebpayTransactionDTO response = new WebpayTransactionDTO("token123", "https://redirect.url", 100.0, "session123", "order123");

        when(webpayService.createTransaction(any(WebpayTransactionDTO.class))).thenReturn(response);

        // Perform POST request
        mockMvc.perform(post("/api/webpay/transactions")
                .contentType("application/json")
                .content("{\"token\":\"token123\",\"urlRedirect\":\"https://redirect.url\",\"amount\":100.0,\"sessionId\":\"session123\",\"buyOrder\":\"order123\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("token123"))
                .andExpect(jsonPath("$.urlRedirect").value("https://redirect.url"));
    }

}
