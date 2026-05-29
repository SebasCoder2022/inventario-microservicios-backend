package com.InventarioModelo.Inventarios.repository;

import com.InventarioModelo.Inventarios.model.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {
}
