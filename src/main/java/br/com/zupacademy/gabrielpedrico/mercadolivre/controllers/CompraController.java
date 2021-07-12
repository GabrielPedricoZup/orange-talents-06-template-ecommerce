package br.com.zupacademy.gabrielpedrico.mercadolivre.controllers;

import br.com.zupacademy.gabrielpedrico.mercadolivre.dtos.CompraRequest;
import br.com.zupacademy.gabrielpedrico.mercadolivre.dtos.CompraResponse;
import br.com.zupacademy.gabrielpedrico.mercadolivre.models.Compra;
import br.com.zupacademy.gabrielpedrico.mercadolivre.models.Produto;
import br.com.zupacademy.gabrielpedrico.mercadolivre.models.Usuario;
import br.com.zupacademy.gabrielpedrico.mercadolivre.repositories.CompraRepository;
import br.com.zupacademy.gabrielpedrico.mercadolivre.repositories.ProdutoRepository;
import br.com.zupacademy.gabrielpedrico.mercadolivre.tools.FakeEmailSender;
import br.com.zupacademy.gabrielpedrico.mercadolivre.tools.FakePaymentGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
public class CompraController {

    @Autowired
    CompraRepository compraRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    @PostMapping("/compra")
    @Transactional
    public ResponseEntity<?> compra(@AuthenticationPrincipal Usuario usuario, @RequestBody @Valid CompraRequest request){

        Compra compra = request.conversor(usuario,produtoRepository);
        String linkCompra = FakePaymentGateway.envia(compra.getToken(),compra.getTipoPagamento());
        Produto produto = produtoRepository.findByNome(request.getProduto());
        produto.movimentaEstoque(request.getQuantidade());
        FakeEmailSender.enviaCompra(produto.getDonoProduto().getLogin());
        produtoRepository.save(produto);
        compraRepository.save(compra);
        CompraResponse response = compra.conversor(linkCompra,produtoRepository,usuario);
        return ResponseEntity.status(302).body(response);
    }
}
