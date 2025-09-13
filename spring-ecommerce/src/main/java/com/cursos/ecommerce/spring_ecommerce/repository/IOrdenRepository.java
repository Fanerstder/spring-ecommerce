package com.cursos.ecommerce.spring_ecommerce.repository;


import com.cursos.ecommerce.spring_ecommerce.models.Orden;
import com.cursos.ecommerce.spring_ecommerce.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrdenRepository extends JpaRepository<Orden, Long> {
    List<Orden> findByUsuario (Usuario usuario);
}
