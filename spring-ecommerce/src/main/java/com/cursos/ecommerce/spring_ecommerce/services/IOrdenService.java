package com.cursos.ecommerce.spring_ecommerce.services;

import com.cursos.ecommerce.spring_ecommerce.models.Orden;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IOrdenService {
    List<Orden> findAll();
    Orden save(Orden orden);
    String generarNumeroOrden();
}
