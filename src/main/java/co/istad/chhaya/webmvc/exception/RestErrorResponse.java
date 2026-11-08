package co.istad.chhaya.webmvc.exception;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;

@Builder
public record RestErrorResponse(
        String message,
        Integer code,
        String status,
        Instant timestamp,
        Object errorDetail

) {
}
