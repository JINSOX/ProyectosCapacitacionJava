package org.acme;

import io.smallrye.jwt.build.Jwt;
import jakarta.inject.Singleton;

@Singleton
public class TokenService {

    public String generateToken() {
        return Jwt.issuer("token")
                .subject("recurso")
                .groups("admin")
                .expiresAt(System.currentTimeMillis()+3600)
                .sign();
    }
}
