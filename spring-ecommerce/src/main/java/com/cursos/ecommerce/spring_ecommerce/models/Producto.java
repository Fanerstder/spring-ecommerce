package com.cursos.ecommerce.spring_ecommerce.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "productos")
@Entity(name = "Producto")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Producto {
    private Long id;
    private String nombre;
    private String descripcion;
    private String imagen;
    private String precio;
    private int cantidad;
}
