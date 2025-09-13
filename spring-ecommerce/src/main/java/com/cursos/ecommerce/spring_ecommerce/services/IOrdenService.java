package com.cursos.ecommerce.spring_ecommerce.services;

import com.cursos.ecommerce.spring_ecommerce.models.Orden;
import com.cursos.ecommerce.spring_ecommerce.models.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IOrdenService {
    List<Orden> findAll();
    Optional<Orden> findById(Long id);
    Orden save(Orden orden);
    String generarNumeroOrden();
    List<Orden> findByUsuario (Usuario usuario);
}
