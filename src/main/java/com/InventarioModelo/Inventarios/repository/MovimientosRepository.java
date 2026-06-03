package com.InventarioModelo.Inventarios.repository;

import com.InventarioModelo.Inventarios.model.Movimientos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface MovimientosRepository extends JpaRepository<Movimientos, Long> {
}
