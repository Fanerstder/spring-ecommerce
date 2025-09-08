package com.cursos.ecommerce.spring_ecommerce.repository;

import com.cursos.ecommerce.spring_ecommerce.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Long> {
}
