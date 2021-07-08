package br.com.zupacademy.gabrielpedrico.mercadolivre.repositories;

import br.com.zupacademy.gabrielpedrico.mercadolivre.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto,Long> {
}
