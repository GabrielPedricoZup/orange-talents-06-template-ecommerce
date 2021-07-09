package br.com.zupacademy.gabrielpedrico.mercadolivre.controllers;

import br.com.zupacademy.gabrielpedrico.mercadolivre.dtos.PerguntaRequest;
import br.com.zupacademy.gabrielpedrico.mercadolivre.models.Opiniao;
import br.com.zupacademy.gabrielpedrico.mercadolivre.models.Pergunta;
import br.com.zupacademy.gabrielpedrico.mercadolivre.models.Usuario;
import br.com.zupacademy.gabrielpedrico.mercadolivre.repositories.PerguntaRepository;
import br.com.zupacademy.gabrielpedrico.mercadolivre.repositories.ProdutoRepository;
import br.com.zupacademy.gabrielpedrico.mercadolivre.tools.FakeEmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class PerguntaController {

    @Autowired
    PerguntaRepository perguntaRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    @PostMapping("/produto/{id}/pergunta")
    public ResponseEntity<?> cadastraPergunta(@RequestBody @Valid PerguntaRequest request, @PathVariable("id") Long id, @AuthenticationPrincipal Usuario usuario){

        Pergunta pergunta = request.conversor(produtoRepository,usuario,id);
        FakeEmailSender.envia(pergunta.getProduto().getDonoProduto().getLogin());
        perguntaRepository.save(pergunta);


        return ResponseEntity.ok().build();
    }
}
