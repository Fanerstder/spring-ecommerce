package com.cursos.ecommerce.spring_ecommerce.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "detalleOrdenes")
@Entity(name = "DetalleOrden")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class DetalleOrden {
    private Long id;
    private String nombre;
    private double cantidad;
    private double precio;
    private double total;
}
