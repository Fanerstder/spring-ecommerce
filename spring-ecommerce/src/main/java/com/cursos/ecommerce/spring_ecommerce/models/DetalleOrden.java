package com.cursos.ecommerce.spring_ecommerce.models;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "detalleOrdenes")
@Entity(name = "DetalleOrden")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class DetalleOrden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private double cantidad;
    private double precio;
    private double total;

    @OneToOne
    private Orden orden;

    @ManyToOne
    private Producto producto;
}
