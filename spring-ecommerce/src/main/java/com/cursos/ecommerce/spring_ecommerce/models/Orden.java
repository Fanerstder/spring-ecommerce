package com.cursos.ecommerce.spring_ecommerce.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "ordenes")
@Entity(name = "Orden")
@Getter
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
