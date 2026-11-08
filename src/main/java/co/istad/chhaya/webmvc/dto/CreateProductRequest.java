package co.istad.chhaya.webmvc.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record CreateProductRequest(
        @NotBlank(message = "Name is required")
        @Size(max = 150)
        String name,
        //Optional for description
        String description,

        @NotNull
        @Positive
        BigDecimal price,

        @Size(max = 255)
        String picture,

        @NotBlank(message = "categoryId is required")
        @Size(max = 255)
        String categoryId
) {
}
