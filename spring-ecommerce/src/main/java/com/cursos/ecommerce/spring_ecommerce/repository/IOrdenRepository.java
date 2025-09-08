package com.cursos.ecommerce.spring_ecommerce.repository;


import com.cursos.ecommerce.spring_ecommerce.models.Orden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrdenRepository extends JpaRepository<Orden, Long> {
}
