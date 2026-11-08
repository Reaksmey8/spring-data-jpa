package co.istad.chhaya.webmvc.dto;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ProductDto(
        @Size(max = 150)
        String name,
        String description,
        @Positive
        BigDecimal price,
        @Size(max = 255)
        String picture,
        @Size(max = 255)
        String categoryId,
        Boolean isAvailable
) {
}
