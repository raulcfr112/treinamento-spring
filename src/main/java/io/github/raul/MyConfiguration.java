package io.github.raul;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@Development
public class MyConfiguration {
    @Bean
    public CommandLineRunner executar(){
        return args -> {
            System.out.println("Rodando a configuração de desenvolvimento");
        };
    }
}
