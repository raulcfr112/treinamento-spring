package io.github.raul.service.impl;

import io.github.raul.domain.entity.Cliente;
import io.github.raul.domain.entity.ItemPedido;
import io.github.raul.domain.entity.Pedido;
import io.github.raul.domain.entity.Produto;
import io.github.raul.domain.enums.StatusPedido;
import io.github.raul.domain.repository.Clientes;
import io.github.raul.domain.repository.ItemsPedido;
import io.github.raul.domain.repository.Pedidos;
import io.github.raul.domain.repository.Produtos;
import io.github.raul.exception.PedidoNaoEncontradoException;
import io.github.raul.exception.RegraNegocioException;
import io.github.raul.rest.dto.ItemPedidoDTO;
import io.github.raul.rest.dto.PedidoDTO;
import io.github.raul.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {
    private final Pedidos pedidosRepository;
    private final Clientes clientesRepository;
    private final Produtos produtosRepository;
    private final ItemsPedido itemsPedidosRepository;

    @Override
    @Transactional
    public Pedido salvar(PedidoDTO dto) {
        Integer idCliente = dto.getCliente();
        Cliente cliente = clientesRepository
                .findById(idCliente)
                .orElseThrow(() -> new RegraNegocioException("Código de cliente inválido."));

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.REALIZADO);

        List<ItemPedido> itemPedidos = converterItems(pedido, dto.getItems());
        pedidosRepository.save(pedido);
        itemsPedidosRepository.saveAll(itemPedidos);
        pedido.setItems(itemPedidos);
        return pedido;
    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
        return pedidosRepository.findByIdFetchItens(id);
    }

    @Override
    @Transactional
    public void atualizaStauts(Integer id, StatusPedido statusPedido) {
        pedidosRepository
                .findById(id)
                .map(pedido -> {
                    pedido.setStatus(statusPedido);
                    return pedidosRepository.save(pedido);
                }).orElseThrow(() -> new PedidoNaoEncontradoException());
    }

    private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items){
        if  (items.isEmpty()){
            throw new RegraNegocioException("Não é possivel realizar um pedido sem items!");
        }

        return items
                .stream()
                .map( dto -> {
                    Integer idProduto = dto.getProduto();
                    Produto produto = produtosRepository.findById(idProduto)
                            .orElseThrow(() -> new RegraNegocioException(
                                    "Código de produto inválido:" + idProduto
                            ));
                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;
                }).collect(Collectors.toList());
    }
}
