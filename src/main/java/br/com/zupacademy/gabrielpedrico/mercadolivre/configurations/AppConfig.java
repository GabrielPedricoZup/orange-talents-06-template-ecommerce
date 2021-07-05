package br.com.zupacademy.gabrielpedrico.mercadolivre.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class AppConfig { // Classe responsável por manter alguma configuração, criar algum componente específico e assim por diante.

    @Bean // É um componente do Spring. Desta forma é possível injetar o BCrypt em outras classes
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
