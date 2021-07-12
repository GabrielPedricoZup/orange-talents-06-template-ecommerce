package br.com.zupacademy.gabrielpedrico.mercadolivre.repositories;

import br.com.zupacademy.gabrielpedrico.mercadolivre.models.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao,Long> {
}
