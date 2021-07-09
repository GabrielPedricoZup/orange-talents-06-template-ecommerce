package br.com.zupacademy.gabrielpedrico.mercadolivre.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class ImagemProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JsonIgnore
    private Produto produto;

    @URL
    @NotBlank
    private String link;

    public ImagemProduto(Produto produto, String link) {
        this.produto = produto;
        this.link = link;
    }

    @Deprecated
    public ImagemProduto(){
    }

    public Long getId() {
        return id;
    }

    public Produto getProduto() {
        return produto;
    }

    public String getLink() {
        return link;
    }

    @Override
    public String toString() {
        return "ImagemProduto{" +
                "id=" + id +
                ", produto=" + produto +
                ", link='" + link + '\'' +
                '}';
    }
}
