package br.com.zupacademy.gabrielpedrico.mercadolivre.repositories;

import br.com.zupacademy.gabrielpedrico.mercadolivre.models.Pergunta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerguntaRepository extends JpaRepository<Pergunta,Long> {
}
