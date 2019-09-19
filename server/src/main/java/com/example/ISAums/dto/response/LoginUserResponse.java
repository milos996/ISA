package com.example.ISAums.dto.response;

import com.example.ISAums.model.enumeration.Role;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class LoginUserResponse {
    private UUID id;

    private String email;

    private String token;

    private boolean isRentACarAdminFirstLogin;

    private String role;
}
