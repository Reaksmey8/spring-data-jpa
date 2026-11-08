package co.istad.chhaya.webmvc.dto;

import lombok.Builder;

import java.util.Set;

@Builder
public record UserResponse<email>(
        String username,
        String email,
        String fullName,
        String gender,
        Set<String> roles

) {
}
