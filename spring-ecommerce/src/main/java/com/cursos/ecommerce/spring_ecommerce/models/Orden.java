package com.cursos.ecommerce.spring_ecommerce.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "ordenes")
@Entity(name = "Orden")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numero;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaRecibida;
    private double total;

    @ManyToOne
    private Usuario usuario;

    @OneToOne
    private DetalleOrden detalle;
}
