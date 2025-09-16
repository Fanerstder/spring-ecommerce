package com.cursos.ecommerce.spring_ecommerce.models;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "detalleOrdenes")
@Entity(name = "DetalleOrden")
@Data
@ToString(exclude = "orden")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class DetalleOrden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private Integer cantidad;
    private double precio;
    private double total;

    @ManyToOne
    @JoinColumn(name = "orden_id")
    private Orden orden;

    @ManyToOne
    private Producto producto;
}
