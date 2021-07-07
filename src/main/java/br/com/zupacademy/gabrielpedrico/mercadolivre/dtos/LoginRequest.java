package br.com.zupacademy.gabrielpedrico.mercadolivre.dtos;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginRequest {

    private String login;
    private String senha;

    public void setLogin(String login) {
        this.login = login;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public UsernamePasswordAuthenticationToken conversor() {

        return new UsernamePasswordAuthenticationToken(this.login,this.senha);
    }
}
