package br.com.zupacademy.gabrielpedrico.mercadolivre.controllers;

import br.com.zupacademy.gabrielpedrico.mercadolivre.dtos.ProdutoRequest;
import br.com.zupacademy.gabrielpedrico.mercadolivre.models.Produto;
import br.com.zupacademy.gabrielpedrico.mercadolivre.models.Usuario;
import br.com.zupacademy.gabrielpedrico.mercadolivre.repositories.CategoriaRepository;
import br.com.zupacademy.gabrielpedrico.mercadolivre.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @PostMapping(value="/produto")
    public ResponseEntity<?> cadastrar(@RequestBody @Valid  ProdutoRequest request,@AuthenticationPrincipal Usuario usuario){

        Produto produto = request.conversor(categoriaRepository,usuario);
        produtoRepository.save(produto);

        return ResponseEntity.ok().build();
    }
}
