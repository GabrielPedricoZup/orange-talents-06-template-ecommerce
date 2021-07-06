package br.com.zupacademy.gabrielpedrico.mercadolivre.controllers;

import br.com.zupacademy.gabrielpedrico.mercadolivre.dtos.CategoriaRequest;
import br.com.zupacademy.gabrielpedrico.mercadolivre.models.Categoria;
import br.com.zupacademy.gabrielpedrico.mercadolivre.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class CategoriaController {

    @Autowired
    CategoriaRepository categoriaRepository;

    @PostMapping(value = "/categoria")
    @Transactional
    public ResponseEntity<?> cadastrar (@RequestBody @Valid CategoriaRequest request){

        Categoria categoria = request.conversor(categoriaRepository);
        categoriaRepository.save(categoria);
        return ResponseEntity.ok().build();
    }
}
