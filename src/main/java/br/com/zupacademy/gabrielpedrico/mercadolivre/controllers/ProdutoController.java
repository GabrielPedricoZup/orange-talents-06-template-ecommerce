package br.com.zupacademy.gabrielpedrico.mercadolivre.controllers;

import br.com.zupacademy.gabrielpedrico.mercadolivre.dtos.ImagensRequest;
import br.com.zupacademy.gabrielpedrico.mercadolivre.dtos.ProdutoRequest;
import br.com.zupacademy.gabrielpedrico.mercadolivre.dtos.ProdutoResponse;
import br.com.zupacademy.gabrielpedrico.mercadolivre.models.Produto;
import br.com.zupacademy.gabrielpedrico.mercadolivre.models.Usuario;
import br.com.zupacademy.gabrielpedrico.mercadolivre.repositories.CategoriaRepository;
import br.com.zupacademy.gabrielpedrico.mercadolivre.repositories.OpiniaoRepository;
import br.com.zupacademy.gabrielpedrico.mercadolivre.repositories.ProdutoRepository;
import br.com.zupacademy.gabrielpedrico.mercadolivre.tools.UploaderFake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private UploaderFake uploaderFake;

    @Autowired
    private OpiniaoRepository opiniaoRepository;

    @PostMapping(value = "/produto")
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid ProdutoRequest request, @AuthenticationPrincipal Usuario usuario) {

        Produto produto = request.conversor(categoriaRepository, usuario);
        produtoRepository.save(produto);

        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/produto/{id}/imagens")
    @Transactional
    public ResponseEntity<?> adicionaImagens(@PathVariable("id") Long id,@RequestBody @Valid ImagensRequest request, @AuthenticationPrincipal Usuario usuario) {
/*  [1] --> Enviar imagens para o local onde vão ficar.
    [2] --> Pegar o link de todas as imagens.
    [3] --> Associar com o produto em questão.
    [4] --> Preciso carregar o produto.
    [5] --> Depois que associar eu preciso atualizar a nova versão do produto.*/

        Set<String> links = uploaderFake.envia(request.getImagens());
        Optional<Produto> produto = produtoRepository.findById(id);
        if (!produto.get().pertenceAoUsuario(usuario) || !produto.isPresent()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        produto.get().associaImagens(links);
        produtoRepository.saveAndFlush(produto.get());

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/produto/{id}")
    public ResponseEntity<?> detalharProduto(@PathVariable("id") Long id) {

        Optional<Produto> possivelProduto = produtoRepository.findById(id);
        if (possivelProduto.isPresent()){
            Produto produto = possivelProduto.get();
            ProdutoResponse response = produto.conversor(opiniaoRepository);
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }
}
