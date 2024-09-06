package com.digitalhouse.clinicaOdontologica.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
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
@Table(name = "odontologos")
public class Odontologo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "Debes ingresar un numero de matricula")
    private String numeroMatricula;
    @NotBlank(message = "Debes ingresar un nombre")
    private String nombre;
    @NotBlank(message = "Debes ingresar un apellido")
    private String apellido;
    @Valid
    @OneToMany(mappedBy = "odontologo")
    //@JsonManagedReference(value = "odontologo-turno")
    @JsonIgnore
    private Set<Turno> turnoSet;


    @Override
    public String toString() {
        return "Odontologo{" +
                "id=" + id +
                ", numeroMatricula='" + numeroMatricula + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                '}';
    }
}
