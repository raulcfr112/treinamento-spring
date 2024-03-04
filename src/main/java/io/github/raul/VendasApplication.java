package io.github.raul;

import io.github.raul.domain.entity.Cliente;
import io.github.raul.domain.entity.Pedido;
import io.github.raul.domain.repository.Clientes;
import io.github.raul.domain.repository.Pedidos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner init(
            @Autowired Clientes clientes,
            @Autowired Pedidos pedidos
    ) {
        return args -> {
            System.out.println("Salvando clientes");
            Cliente raul = new Cliente("Raul");
            clientes.save(raul);

            Pedido p = new Pedido();
            p.setCliente(raul);
            p.setDataPedido(LocalDate.now());
            p.setTotal(BigDecimal.valueOf(100));

            pedidos.save(p);

            Cliente cliente = clientes.findClienteFetchPedidos(raul.getId());
            System.out.println(cliente);
            System.out.println(cliente.getPedidos());


        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}