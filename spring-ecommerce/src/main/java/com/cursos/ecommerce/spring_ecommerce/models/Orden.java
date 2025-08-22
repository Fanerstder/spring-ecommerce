package com.cursos.ecommerce.spring_ecommerce.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
    private Long id;
    private String numero;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaRecibida;
    private double total;
}
