package com.cursos.ecommerce.spring_ecommerce.models;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "productos")
@Entity(name = "Producto")
@Data
@ToString(exclude = "usuario")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;
    private String imagen;
    private double precio;
    private int cantidad;

    @ManyToOne
    private Usuario usuario;

}
