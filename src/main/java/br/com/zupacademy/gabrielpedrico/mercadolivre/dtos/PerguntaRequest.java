package br.com.zupacademy.gabrielpedrico.mercadolivre.dtos;

import br.com.zupacademy.gabrielpedrico.mercadolivre.models.Opiniao;
import br.com.zupacademy.gabrielpedrico.mercadolivre.models.Pergunta;
import br.com.zupacademy.gabrielpedrico.mercadolivre.models.Produto;
import br.com.zupacademy.gabrielpedrico.mercadolivre.models.Usuario;
import br.com.zupacademy.gabrielpedrico.mercadolivre.repositories.ProdutoRepository;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;
import java.util.Optional;

public class PerguntaRequest {

    @NotBlank
    private String titulo;

    @NotBlank
    private String descricao;

    @ManyToOne
    private Produto produto;

    @ManyToOne
    private Usuario usuario;

    public PerguntaRequest(String titulo, String descricao) {
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Produto getProduto() {
        return produto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Pergunta conversor(ProdutoRepository produtoRepository,Usuario usuario,Long idProduto){

        Optional<Produto> produto = produtoRepository.findById(idProduto);
        if(!produto.isPresent()){
            throw new IllegalArgumentException("Produto inexistente");
        }
        return new Pergunta(this.titulo,this.descricao,produto.get(),usuario);
    }

}
