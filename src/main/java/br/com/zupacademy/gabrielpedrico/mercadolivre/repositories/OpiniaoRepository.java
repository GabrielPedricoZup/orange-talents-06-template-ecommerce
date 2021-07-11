package br.com.zupacademy.gabrielpedrico.mercadolivre.repositories;

import br.com.zupacademy.gabrielpedrico.mercadolivre.models.Opiniao;
import br.com.zupacademy.gabrielpedrico.mercadolivre.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;

public interface OpiniaoRepository extends JpaRepository<Opiniao,Long> {
    List<Opiniao> findAllByProduto(Produto produto);
}
