package com.guiFerranti.SpringEventPro.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.guiFerranti.SpringEventPro.domain.user.DadosLogin;
import com.guiFerranti.SpringEventPro.domain.user.User;
import com.guiFerranti.SpringEventPro.infra.exception.Exceptions;
import com.guiFerranti.SpringEventPro.infra.repository.UserRepository;
import com.guiFerranti.SpringEventPro.infra.security.TokenJWTData;
import com.guiFerranti.SpringEventPro.infra.security.TokenService;
import jakarta.servlet.http.HttpServletRequest;
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
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;
    public TokenJWTData doLogin(@RequestBody @Valid DadosLogin dados) {
        var authToken = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
        var auth = manager.authenticate(authToken);

        var tokenJWT = tokenService.tokenGenerate((User) auth.getPrincipal());

        return new TokenJWTData(tokenJWT);
    }

    private String extractEmailFromToken(String token) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getSubject();
    }

    public User getUserByToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization").replace("Bearer ", "");
        var email = this.extractEmailFromToken(authorizationHeader);
        return userRepository.findCompleteUserByEmail(email);
    }

    public void ownerOrAdminOnly(Long id, HttpServletRequest request) {
        if (!(this.getUserByToken(request).getId().equals(id) || this.getUserByToken(request).getRole().name().equals("ADMIN"))) {

            throw new Exceptions.NotAuthorizedUser("Only the owner user or administrator can perform this action");
        }
    }

}