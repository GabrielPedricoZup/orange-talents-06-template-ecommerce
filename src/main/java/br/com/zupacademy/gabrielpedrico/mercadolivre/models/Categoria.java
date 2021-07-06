package br.com.zupacademy.gabrielpedrico.mercadolivre.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank
    @Column(unique = true)
    String nome;

    @ManyToOne
    Categoria categoria;


    public Categoria(String nome, Categoria categoria) {
        this.nome = nome;
        this.categoria = categoria;

    }

    @Deprecated
    public Categoria(){
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Categoria getCategoria() {
        return categoria;
    }
}
