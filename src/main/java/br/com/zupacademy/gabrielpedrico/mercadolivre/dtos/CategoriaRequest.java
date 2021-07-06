package br.com.zupacademy.gabrielpedrico.mercadolivre.dtos;

import br.com.zupacademy.gabrielpedrico.mercadolivre.models.Categoria;
import br.com.zupacademy.gabrielpedrico.mercadolivre.repositories.CategoriaRepository;
import br.com.zupacademy.gabrielpedrico.mercadolivre.validators.Exists;
import br.com.zupacademy.gabrielpedrico.mercadolivre.validators.ExistsValidator;
import br.com.zupacademy.gabrielpedrico.mercadolivre.validators.NotExists;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public class CategoriaRequest {

    @NotBlank
    @NotExists(domainClass = Categoria.class,fieldName = "nome",message="Categoria já cadastrada")
    String nome;

    String categoria;

    public CategoriaRequest(String nome,String categoria) {
        this.nome = nome;
        this.categoria = categoria;
    }

    @Deprecated
    public CategoriaRequest(){
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Categoria conversor(CategoriaRepository categoriaRepository){

        if(!isCategoriaNull()){
            Categoria categoria = categoriaRepository.findByNome(this.categoria);
            if(isCategoriaValid(categoria)) {
                return new Categoria(this.nome, categoria);
            }else{
                throw new IllegalArgumentException("Categoria mãe inexistente");
            }
        }
        return new Categoria(this.nome,null);

    }

    public boolean isCategoriaNull() {

        if(this.categoria == null){
            return true;
        }
        return false;
    }

    public boolean isCategoriaValid(Categoria categoria){
        if(categoria == null){
            return false;
        }
        return true;
    }
}
