package br.com.zupacademy.gabrielpedrico.mercadolivre.dtos;

import br.com.zupacademy.gabrielpedrico.mercadolivre.models.Compra;
import br.com.zupacademy.gabrielpedrico.mercadolivre.models.Produto;
import br.com.zupacademy.gabrielpedrico.mercadolivre.models.Usuario;
import br.com.zupacademy.gabrielpedrico.mercadolivre.repositories.CompraRepository;
import br.com.zupacademy.gabrielpedrico.mercadolivre.repositories.ProdutoRepository;
import br.com.zupacademy.gabrielpedrico.mercadolivre.validators.Exists;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class CompraRequest {

    @Exists(domainClass = Produto.class,fieldName = "nome",message = "Produto inexistente")
    String produto;
    @Positive
    Integer quantidade;
    @NotBlank
    String tipoPagamento;

    public CompraRequest(String produto, String tipoPagamento,Integer quantidade) {
        this.produto = produto;
        this.tipoPagamento = tipoPagamento;
        this.quantidade = quantidade;
    }

    public Compra conversor(Usuario usuario, ProdutoRepository produtoRepository){

        Produto produto = produtoRepository.findByNome(this.produto);
        if(produto.getQuantidade() >= this.quantidade){
            return new Compra(produto,usuario,this.tipoPagamento);
        }
        throw new IllegalArgumentException("Quantidade maior que quantidade em estoque");

    }

    public String getProduto() {
        return produto;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public Integer getQuantidade() {
        return quantidade;
    }
}
