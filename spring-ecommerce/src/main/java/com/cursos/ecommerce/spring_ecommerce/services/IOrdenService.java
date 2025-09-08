package com.cursos.ecommerce.spring_ecommerce.services;

import com.cursos.ecommerce.spring_ecommerce.models.Orden;
import org.springframework.stereotype.Service;

@Service
public interface IOrdenService {
    Orden save(Orden orden);
}
