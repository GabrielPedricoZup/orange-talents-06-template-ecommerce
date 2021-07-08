package br.com.zupacademy.gabrielpedrico.mercadolivre.dtos;

import br.com.zupacademy.gabrielpedrico.mercadolivre.models.Categoria;
import br.com.zupacademy.gabrielpedrico.mercadolivre.models.Produto;
import br.com.zupacademy.gabrielpedrico.mercadolivre.models.Usuario;
import br.com.zupacademy.gabrielpedrico.mercadolivre.repositories.CategoriaRepository;
import br.com.zupacademy.gabrielpedrico.mercadolivre.validators.Exists;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.*;

public class ProdutoRequest {

    @NotBlank
    private String nome;

    @PositiveOrZero
    private Integer quantidade;

    @NotBlank
    @Length(max = 1000)
    private String descricao;

    @NotNull
    @Positive
    private BigDecimal valor;

    @NotNull
    @Exists(domainClass = Categoria.class, fieldName = "id")
    private Long idCategoria;

    @Size(min = 3)
    List<CaracteristicaRequest> caracteristicas = new ArrayList<>();

    public ProdutoRequest(String nome, Integer quantidade, String descricao, BigDecimal valor, Long idCategoria,List<CaracteristicaRequest> caracteristicas) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.valor = valor;
        this.idCategoria = idCategoria;
        this.caracteristicas.addAll(caracteristicas);
    }

    public Produto conversor(CategoriaRepository categoriaRepository, Usuario usuario) {
        var categoria = categoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new IllegalArgumentException("Categoria Inexistente"));

        return new Produto(nome, quantidade, descricao, valor, categoria, usuario,this.caracteristicas);
        /*    if (categoria.isPresent()) {
                return new Produto(nome, quantidade, descricao, valor, categoria.get(),usuario);
            }*/
        /* throw new IllegalArgumentException("Categoria inexistente");*/

    }


}
