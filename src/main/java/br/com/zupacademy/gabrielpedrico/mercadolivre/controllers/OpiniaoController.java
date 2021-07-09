package br.com.zupacademy.gabrielpedrico.mercadolivre.controllers;

import br.com.zupacademy.gabrielpedrico.mercadolivre.dtos.OpiniaoRequest;
import br.com.zupacademy.gabrielpedrico.mercadolivre.models.Opiniao;
import br.com.zupacademy.gabrielpedrico.mercadolivre.models.Usuario;
import br.com.zupacademy.gabrielpedrico.mercadolivre.repositories.OpiniaoRepository;
import br.com.zupacademy.gabrielpedrico.mercadolivre.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class OpiniaoController {

    @Autowired
    OpiniaoRepository opiniaoRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    @PostMapping(value = "/produto/{id}/opiniao")
    public ResponseEntity<?> cadastraOpiniao(@RequestBody @Valid OpiniaoRequest request, @PathVariable("id") Long id, @AuthenticationPrincipal Usuario usuario){

        Opiniao opiniao = request.conversor(produtoRepository,usuario,id);
        opiniaoRepository.save(opiniao);

        return ResponseEntity.ok().build();
    }
}
