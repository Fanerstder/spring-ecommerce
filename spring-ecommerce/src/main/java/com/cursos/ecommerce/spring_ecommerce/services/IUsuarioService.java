package com.cursos.ecommerce.spring_ecommerce.services;

import com.cursos.ecommerce.spring_ecommerce.models.Usuario;

import java.util.Optional;


public interface IUsuarioService {

    Optional<Usuario> findById(Long id);
}
