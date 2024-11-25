package ru.evanemo.st_test.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
@Getter
public class RolesGrantedAuthorityAdapter implements GrantedAuthority {
    private final String authority;
}