package br.com.zupacademy.gabrielpedrico.mercadolivre.dtos;

import br.com.zupacademy.gabrielpedrico.mercadolivre.models.Usuario;
import br.com.zupacademy.gabrielpedrico.mercadolivre.validators.NotExists;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UsuarioRequest {

    @NotBlank
    @Email
    @NotExists(domainClass = Usuario.class, fieldName = "login",message = "Email já cadastrado")
    private String login;

    @NotBlank
    @Size(min=6)
    private String senha;

    public UsuarioRequest(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    @Deprecated
    public UsuarioRequest(){}

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Usuario conversor(BCryptPasswordEncoder bCryptPasswordEncoder) {
        String password = bCryptPasswordEncoder.encode(this.senha);
        return new Usuario(this.login,password);
    }
}
