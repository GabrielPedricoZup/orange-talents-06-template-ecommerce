package br.com.zupacademy.gabrielpedrico.mercadolivre.dtos;

import br.com.zupacademy.gabrielpedrico.mercadolivre.models.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ProdutoResponse {

    private String nome;

    private Integer quantidade;

    private String descricao;

    private BigDecimal valor;

    private String categoria;

    private String donoProduto;

    private Set<Caracteristica> caracteristicas = new HashSet<>();

    private Set<ImagemProduto> imagens = new HashSet<>();

    private List<Opiniao> opinioes = new ArrayList<>();

    private Integer numeroOpinioes;

    private Double notaMedia;

    public ProdutoResponse(String nome, Integer quantidade, String descricao, BigDecimal valor, String categoria, String donoProduto, Set<Caracteristica> caracteristicas, Set<ImagemProduto> imagens, List<Opiniao> opinioes) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.valor = valor;
        this.categoria = categoria;
        this.donoProduto = donoProduto;
        this.caracteristicas = caracteristicas;
        this.imagens = imagens;
        this.opinioes.addAll(opinioes);
        this.numeroOpinioes = opinioes.size();
        this.notaMedia = opinioes.stream().map(Opiniao::getNota)
                                          .collect(Collectors.averagingInt(nota -> nota));



    }

    public String getNome() {
        return nome;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getDonoProduto() {
        return donoProduto;
    }

    public Set<Caracteristica> getCaracteristicas() {
        return caracteristicas;
    }

    public Set<ImagemProduto> getImagens() {
        return imagens;
    }

    public List<Opiniao> getOpinioes() {
        return opinioes;
    }

    public Integer getNumeroOpinioes() {
        return numeroOpinioes;
    }

    public Double getNotaMedia() {
        return notaMedia;
    }
}
