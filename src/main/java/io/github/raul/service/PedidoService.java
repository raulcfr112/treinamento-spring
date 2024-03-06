package io.github.raul.service;

import io.github.raul.domain.entity.Pedido;
import io.github.raul.domain.enums.StatusPedido;
import io.github.raul.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {
    Pedido salvar(PedidoDTO dto);
    Optional<Pedido> obterPedidoCompleto(Integer id);
    void atualizaStatus(Integer id, StatusPedido statusPedido);
}
