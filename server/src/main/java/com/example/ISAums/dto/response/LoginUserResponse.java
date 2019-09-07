package com.example.ISAums.dto.response;

import com.example.ISAums.model.enumeration.Role;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class LoginUserResponse {
    private String email;

    private String token;

    private String role;
}
