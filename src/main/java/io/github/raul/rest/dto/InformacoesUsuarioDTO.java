package io.github.raul.rest.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InformacoesUsuarioDTO {
    private String username;
    private boolean admin;
}
