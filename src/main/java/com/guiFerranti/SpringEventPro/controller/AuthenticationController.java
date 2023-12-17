package com.guiFerranti.SpringEventPro.controller;

import com.guiFerranti.SpringEventPro.domain.user.DadosLogin;
import com.guiFerranti.SpringEventPro.domain.user.User;
import com.guiFerranti.SpringEventPro.infra.security.TokenJWTData;
import com.guiFerranti.SpringEventPro.infra.security.TokenService;
import com.guiFerranti.SpringEventPro.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class AuthenticationController {

    @Autowired
    private AuthService authService;

    @PostMapping
    public ResponseEntity doLogin(@RequestBody @Valid DadosLogin dados) {

        return ResponseEntity.ok(authService.doLogin(dados));
    }

}
