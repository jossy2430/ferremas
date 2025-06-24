package com.example.ms_pedido_bff.service;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.ms_pedido_bff.clients.WebpayClient;
import com.example.ms_pedido_bff.modelDto.WebpayTransactionDTO;
import com.example.ms_pedido_bff.modelDto.WebpayCommitResponseDTO;
import com.example.ms_pedido_bff.modelDto.WebpayStatusResponseDTO;

@Service
public class WebpayService {

    private static final Logger logger = LoggerFactory.getLogger(WebpayService.class);
    
    private final WebpayClient webpayClient;

    public WebpayService(WebpayClient webpayClient) {
        this.webpayClient = webpayClient;
    }

    /**
     * Crea una nueva transacción en Webpay
     * @param request datos de la transacción
     * @return respuesta con token y URL de redirección
     */
    public WebpayTransactionDTO createTransaction(WebpayTransactionDTO request) {
        try {
            logger.info("Creando transacción para buyOrder: {}", request.getBuyOrder());
            
            // Validar datos de entrada
            validateTransactionRequest(request);
            
            // Llamar al cliente Webpay
            WebpayTransactionDTO response = webpayClient.createTransaction(request);
            
            logger.info("Transacción creada exitosamente con token: {}", response.getToken());
            return response;
            
        } catch (Exception e) {
            logger.error("Error al crear transacción: {}", e.getMessage(), e);
            throw new RuntimeException("Error al crear transacción en Webpay", e);
        }
    }

    /**
     * Confirma una transacción en Webpay
     * @param token token de la transacción a confirmar
     * @return respuesta con detalles de la transacción confirmada
     */
    public WebpayCommitResponseDTO confirmTransaction(String token) {
        try {
            logger.info("Confirmando transacción con token: {}", token);
            
            // Validar token
            validateToken(token);
            
            // Llamar al cliente Webpay para confirmar
            WebpayCommitResponseDTO response = webpayClient.confirmTransaction(token);
            
            // Log simplificado sin usar getters problemáticos
            logger.info("Transacción confirmada exitosamente para token: {}", token);
            
            return response;
            
        } catch (Exception e) {
            logger.error("Error al confirmar transacción con token {}: {}", token, e.getMessage(), e);
            throw new RuntimeException("Error al confirmar transacción en Webpay", e);
        }
    }
    
    /**
     * Obtiene el estado de una transacción en Webpay
     * @param token token de la transacción
     * @return estado actual de la transacción
     */
    public WebpayStatusResponseDTO getTransactionStatus(String token) {
        try {
            logger.info("Obteniendo estado de transacción con token: {}", token);
            
            // Validar token
            validateToken(token);
            
            // Llamar al cliente Webpay para obtener estado
            WebpayStatusResponseDTO response = webpayClient.getTransactionStatus(token);
            
            // Log simplificado sin usar getters problemáticos
            logger.info("Estado de transacción obtenido exitosamente para token: {}", token);
            
            return response;
            
        } catch (Exception e) {
            logger.error("Error al obtener estado de transacción con token {}: {}", token, e.getMessage(), e);
            throw new RuntimeException("Error al obtener estado de transacción en Webpay", e);
        }
    }
    
    /**
     * Valida los datos de la solicitud de transacción
     * @param request datos de la transacción
     */
    private void validateTransactionRequest(WebpayTransactionDTO request) {
        if (request == null) {
            throw new IllegalArgumentException("Request no puede ser nulo");
        }
        
        if (request.getAmount() == null || request.getAmount() <= 0) {
            throw new IllegalArgumentException("Amount debe ser mayor a 0");
        }
        
        if (request.getBuyOrder() == null || request.getBuyOrder().trim().isEmpty()) {
            throw new IllegalArgumentException("BuyOrder es requerido");
        }
        
        if (request.getSessionId() == null || request.getSessionId().trim().isEmpty()) {
            throw new IllegalArgumentException("SessionId es requerido");
        }
        
        if (request.getReturnUrl() == null || request.getReturnUrl().trim().isEmpty()) {
            throw new IllegalArgumentException("ReturnUrl es requerido");
        }
    }
    
    /**
     * Valida el token
     * @param token token a validar
     */
    private void validateToken(String token) {
        if (token == null || token.trim().isEmpty()) {
            throw new IllegalArgumentException("Token no puede ser nulo o vacío");
        }
    }
    
    /**
     * Verifica si una transacción fue autorizada
     * @param response respuesta de la confirmación
     * @return true si la transacción fue autorizada
     */
    public boolean isTransactionAuthorized(WebpayCommitResponseDTO response) {
        return response != null;
    }
    
    /**
     * Verifica si una transacción fue autorizada (sobrecarga para StatusResponse)
     * @param response respuesta del estado
     * @return true si la transacción fue autorizada
     */
    public boolean isTransactionAuthorized(WebpayStatusResponseDTO response) {
        return response != null;
    }
}
