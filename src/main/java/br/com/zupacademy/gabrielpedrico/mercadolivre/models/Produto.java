package br.com.zupacademy.gabrielpedrico.mercadolivre.models;

import br.com.zupacademy.gabrielpedrico.mercadolivre.dtos.CaracteristicaRequest;
import br.com.zupacademy.gabrielpedrico.mercadolivre.validators.Exists;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.*;
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

    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<ImagemProduto> imagens = new HashSet<>();

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

    public void associaImagens(Set<String> links) {
        Set<ImagemProduto> imagens = links.stream()
                .map(link -> new ImagemProduto(this,link))
                .collect(Collectors.toSet());

        this.imagens.addAll(imagens);
    }

    @Override
    public String toString() {
        return "Produto{" +
                "Id=" + Id +
                ", nome='" + nome + '\'' +
                ", quantidade=" + quantidade +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                ", categoria=" + categoria +
                ", donoProduto=" + donoProduto +
                ", caracteristicas=" + caracteristicas +
                ", imagens=" + imagens +
                '}';
    }

    public boolean pertenceAoUsuario(Usuario usuario) {
        if(usuario.getId() == donoProduto.getId()){
            return true;
        }
        return false;
    }

    public Set<ImagemProduto> getImagens() {
        return imagens;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Id.equals(produto.Id) && donoProduto.equals(produto.donoProduto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, donoProduto);
    }
}
