package br.com.zupacademy.gabrielpedrico.mercadolivre.repositories;

import br.com.zupacademy.gabrielpedrico.mercadolivre.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
}
