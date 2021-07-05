package br.com.zupacademy.gabrielpedrico.mercadolivre.models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String login;

    @NotBlank
    @Size(min = 6)
    private String senha;

    @CreationTimestamp
    @PastOrPresent
    private LocalDateTime dataCadastro;

    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    @Deprecated
    public Usuario() {
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                ", dataCadastro=" + dataCadastro +
                '}';
    }
}
