package com.digitalhouse.clinicaOdontologica.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Especialidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String tipo;
    @ManyToMany(mappedBy = "especialidades")
    @JsonIgnoreProperties("especialidades")
    private Set<Odontologo> odontologos;
}
