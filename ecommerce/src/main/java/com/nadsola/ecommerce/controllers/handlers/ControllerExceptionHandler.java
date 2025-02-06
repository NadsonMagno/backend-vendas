package com.nadsola.ecommerce.controllers.handlers;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.nadsola.ecommerce.dto.CustomError;
import com.nadsola.ecommerce.services.exceptions.DataBaseException;
import com.nadsola.ecommerce.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Classe responsável por tratar exceções lançadas pelos controladores da aplicação.
 * Utiliza a anotação @ControllerAdvice para permitir que essa classe seja um componente
 * que contém código de tratamento global de exceções.
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    /**
     * Método que trata exceções do tipo ResourceNotFoundException.
     * 
     * @param e A exceção lançada quando um recurso não é encontrado.
     * @param request O objeto HttpServletRequest que contém informações sobre a requisição HTTP.
     * @return ResponseEntity contendo um objeto CustomError com detalhes sobre a exceção.
     * 
     * Este método é acionado automaticamente quando uma exceção do tipo ResourceNotFoundException
     * é lançada em qualquer controlador da aplicação. Ele cria um objeto CustomError com informações
     * detalhadas sobre o erro, incluindo o timestamp, o status HTTP, a mensagem de erro e a URI da requisição.
     * Em seguida, retorna uma ResponseEntity com o status HTTP 404 (Not Found) e o objeto CustomError no corpo da resposta.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        String error = "Resource not found"; // Mensagem de erro descritiva
        HttpStatus status = HttpStatus.NOT_FOUND; // Define o status HTTP como 404 (Not Found)
        CustomError err = new CustomError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI()); // Cria o objeto de erro personalizado
        return ResponseEntity.status(status).body(err); // Retorna a resposta com o status e o corpo do erro
    }

    /**
     * Método que trata exceções do tipo DataBaseException.
     * 
     * @param e A exceção lançada quando ocorre um erro no banco de dados.
     * @param request O objeto HttpServletRequest que contém informações sobre a requisição HTTP.
     * @return ResponseEntity contendo um objeto CustomError com detalhes sobre a exceção.
     * 
     * Este método é acionado automaticamente quando uma exceção do tipo DataBaseException
     * é lançada em qualquer controlador da aplicação. Ele cria um objeto CustomError com informações
     * detalhadas sobre o erro, incluindo o timestamp, o status HTTP, a mensagem de erro e a URI da requisição.
     * Em seguida, retorna uma ResponseEntity com o status HTTP 400 (Bad Request) e o objeto CustomError no corpo da resposta.
     */
    @ExceptionHandler(DataBaseException.class)
    public ResponseEntity<CustomError> database(DataBaseException e, HttpServletRequest request) {
        String error = "Database error"; // Mensagem de erro descritiva
        HttpStatus status = HttpStatus.BAD_REQUEST; // Define o status HTTP como 400 (Bad Request)
        CustomError err = new CustomError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI()); // Cria o objeto de erro personalizado
        return ResponseEntity.status(status).body(err); // Retorna a resposta com o status e o corpo do erro
    }

}
