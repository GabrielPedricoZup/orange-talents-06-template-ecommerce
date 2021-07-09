package br.com.zupacademy.gabrielpedrico.mercadolivre.dtos;

import br.com.zupacademy.gabrielpedrico.mercadolivre.models.Opiniao;
import br.com.zupacademy.gabrielpedrico.mercadolivre.models.Produto;
import br.com.zupacademy.gabrielpedrico.mercadolivre.models.Usuario;
import br.com.zupacademy.gabrielpedrico.mercadolivre.repositories.ProdutoRepository;
import br.com.zupacademy.gabrielpedrico.mercadolivre.validators.Exists;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.*;
import java.util.Optional;

public class OpiniaoRequest {

    @NotBlank
    private String titulo;

    @NotBlank
    @Size(max = 500)
    private String descricao;

    @Min(1)
    @Max(5)
    private Integer nota;

    //@NotNull
    //@Exists(domainClass = Produto.class, fieldName = "id")
    private Long idProduto;

    //@NotNull
    private Usuario usuario;

    public OpiniaoRequest(String titulo, String descricao,Integer nota) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.nota = nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Long getProduto() {
        return idProduto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Integer getNota() {
        return nota;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Opiniao conversor(ProdutoRepository produtoRepository, Usuario usuario,Long idProduto){
        Optional<Produto> produto = produtoRepository.findById(idProduto);
       if(!produto.isPresent()){
           throw new IllegalArgumentException("Produto inexistente");
       }
        return new Opiniao(this.titulo,this.descricao,produto.get(),usuario,this.nota);
    }
}
