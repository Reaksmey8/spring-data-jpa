package co.istad.chhaya.webmvc.dto;

import jakarta.validation.constraints.NotNull;

public record UpdateStatusRequest(
        @NotNull(message = "status is required")
        Boolean status
) {
}
