package br.com.zupacademy.gabrielpedrico.mercadolivre.models;

import br.com.zupacademy.gabrielpedrico.mercadolivre.dtos.CaracteristicaRequest;
import br.com.zupacademy.gabrielpedrico.mercadolivre.validators.Exists;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false)
    private String nome;

    private Integer quantidade;

    @Column(nullable = false,length = 1000)
    private String descricao;

    @Column(nullable = false)
    private BigDecimal valor;

    @ManyToOne
    private Categoria categoria;

    @ManyToOne
    private Usuario donoProduto;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    private Set<Caracteristica> caracteristicas = new HashSet<>();

    public Produto(String nome, Integer quantidade, String descricao, BigDecimal valor, Categoria categoria, Usuario usuario, Collection<CaracteristicaRequest> caracteristicas ) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.valor = valor;
        this.categoria = categoria;
        this.donoProduto = usuario;
        Set<Caracteristica> novasCaracteristicas = caracteristicas.stream().map(caracteristica -> caracteristica.conversor(this))
                .collect(Collectors.toSet());
        this.caracteristicas.addAll(novasCaracteristicas);
    }

    @Deprecated
    public Produto() {
    }

    public Long getId() {
        return Id;
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

    public Categoria getCategoria() {
        return categoria;
    }

    public Usuario getDonoProduto() {
        return donoProduto;
    }

    public Set<Caracteristica> getCaracteristicas() {
        return caracteristicas;
    }
}
