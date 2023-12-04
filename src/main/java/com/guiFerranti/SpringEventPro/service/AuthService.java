package com.guiFerranti.SpringEventPro.service;

import com.guiFerranti.SpringEventPro.domain.user.DadosLogin;
import com.guiFerranti.SpringEventPro.domain.user.User;
import com.guiFerranti.SpringEventPro.infra.security.TokenJWTData;
import com.guiFerranti.SpringEventPro.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;
    public TokenJWTData doLogin(@RequestBody @Valid DadosLogin dados) {
        var authToken = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
        var auth = manager.authenticate(authToken);

        var tokenJWT = tokenService.tokenGenerate((User) auth.getPrincipal());

        return new TokenJWTData(tokenJWT);
    }

}