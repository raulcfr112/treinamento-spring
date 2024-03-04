package io.github.raul.domain.repository;

import io.github.raul.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemsPedido  extends JpaRepository<ItemPedido, Integer> {
}
