package br.com.zupacademy.gabrielpedrico.mercadolivre.controllers;

import br.com.zupacademy.gabrielpedrico.mercadolivre.configurations.AppConfig;
import br.com.zupacademy.gabrielpedrico.mercadolivre.dtos.UsuarioRequest;
import br.com.zupacademy.gabrielpedrico.mercadolivre.models.Usuario;
import br.com.zupacademy.gabrielpedrico.mercadolivre.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping(value = "/usuario")
    @Transactional
    public ResponseEntity<?> cadastrar (@RequestBody @Valid UsuarioRequest request){

        Usuario usuario = request.conversor(bCryptPasswordEncoder);
        usuarioRepository.save(usuario);
        return ResponseEntity.ok().build();
    }

}
