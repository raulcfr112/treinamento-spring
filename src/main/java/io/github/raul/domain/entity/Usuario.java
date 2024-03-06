package io.github.raul.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty(message = "{campo.login.obrigatorio}")
    @Column
    private String login;

    @NotEmpty(message = "{campo.senha.obrigatorio}")
    @Column
    private String senha;

    @Column
    private boolean admin;

}
