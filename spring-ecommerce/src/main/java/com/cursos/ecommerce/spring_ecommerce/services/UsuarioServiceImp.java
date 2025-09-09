package com.cursos.ecommerce.spring_ecommerce.services;

import com.cursos.ecommerce.spring_ecommerce.models.Usuario;
import com.cursos.ecommerce.spring_ecommerce.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImp implements IUsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
}
