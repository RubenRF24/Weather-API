package com.rubenrf.weather_api.exception;

import java.io.Serial;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@AllArgsConstructor
@Setter
public class ApplicationException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    private final HttpStatus status;

    public ApplicationException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
