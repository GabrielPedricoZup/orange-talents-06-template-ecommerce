package br.com.zupacademy.gabrielpedrico.mercadolivre.controllers;

import br.com.zupacademy.gabrielpedrico.mercadolivre.configurations.security.TokenService;
import br.com.zupacademy.gabrielpedrico.mercadolivre.dtos.LoginRequest;
import br.com.zupacademy.gabrielpedrico.mercadolivre.dtos.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping(value = "/auth")
    public ResponseEntity<TokenResponse> autenticar(@RequestBody @Valid LoginRequest request) {
        UsernamePasswordAuthenticationToken login = request.conversor();
        try {
            Authentication authentication = authManager.authenticate(login);
            String token = tokenService.gerarToken(authentication);
            return ResponseEntity.ok(new TokenResponse(token,"Bearer"));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }


    }
}
