package com.rubenrf.weather_api.exception;

import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.rubenrf.weather_api.dto.ErrorDTO;

import io.github.resilience4j.ratelimiter.RequestNotPermitted;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorDTO> appExceptionHandler(ApplicationException ex) {
        String message = ex.getMessage();
        HttpStatus httpStatus = ex.getStatus();
        ErrorDTO errorDTO = buildErrorDTO(message, httpStatus);
        return new ResponseEntity<>(errorDTO, httpStatus);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorDTO> missingServletRequestParameterExceptionHandler(
            MissingServletRequestParameterException ex) {
        String message = "Required parameter '" + ex.getParameterName() + "' is not missing.";
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ErrorDTO errorDTO = buildErrorDTO(message, httpStatus);
        return new ResponseEntity<>(errorDTO, httpStatus);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorDTO> noResourceFoundExceptionHandler(NoResourceFoundException ex) {
        String message = ex.getMessage();
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        ErrorDTO errorDTO = buildErrorDTO(message, httpStatus);
        return new ResponseEntity<>(errorDTO, httpStatus);
    }

    @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
    public ResponseEntity<ErrorDTO> handleHttpClientErrorUnauthorizedException(
            HttpClientErrorException.Unauthorized ex) {
        String message = "Make sure you have configured a valid API_KEY";
        HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
        ErrorDTO errorDTO = buildErrorDTO(message, httpStatus);
        return new ResponseEntity<>(errorDTO, httpStatus);
    }

    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    public ResponseEntity<ErrorDTO> handleHttpClientErrorBadRequestException(
            HttpClientErrorException.BadRequest ex) {
        String message = ex.getMessage();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ErrorDTO errorDTO = buildErrorDTO(message, httpStatus);
        return new ResponseEntity<>(errorDTO, httpStatus);
    }

    @ExceptionHandler(RequestNotPermitted.class)
    public ResponseEntity<ErrorDTO> handleRequestNotPermittedException(
            RequestNotPermitted ex) {
        String message = ex.getMessage();
        HttpStatus httpStatus = HttpStatus.TOO_MANY_REQUESTS;
        ErrorDTO errorDTO = buildErrorDTO(message, httpStatus);
        return new ResponseEntity<>(errorDTO, httpStatus);
    }

    @ExceptionHandler(RedisConnectionFailureException.class)
    public ResponseEntity<ErrorDTO> handleRedisConnectionFailureException(
            RedisConnectionFailureException ex) {
        String message = "Make sure REDIS server is up and running, then again make the request.";
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ErrorDTO errorDTO = buildErrorDTO(message, httpStatus);
        return new ResponseEntity<>(errorDTO, httpStatus);
    }

    private ErrorDTO buildErrorDTO(String message, HttpStatus status) {
        return ErrorDTO.builder().successStatus(false).message(message).httpStatus(status).build();
    }
}
