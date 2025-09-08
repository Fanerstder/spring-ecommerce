package com.cursos.ecommerce.spring_ecommerce.repository;

import com.cursos.ecommerce.spring_ecommerce.models.DetalleOrden;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDetalleOrdenRepository extends JpaRepository<DetalleOrden, Long> {
}
