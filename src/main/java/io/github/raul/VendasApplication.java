package io.github.raul;

import io.github.raul.domain.entity.Cliente;
import io.github.raul.domain.repository.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class VendasApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}