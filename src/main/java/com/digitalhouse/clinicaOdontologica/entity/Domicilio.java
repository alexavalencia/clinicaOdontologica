package com.digitalhouse.clinicaOdontologica.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "domicilios")
public class Domicilio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "Debes ingresar una calle")
    private String calle;
    @NotNull(message = "Debes ingresar un numero")
    private Integer numero;
    @NotBlank(message = "Debes ingresar una localidad")
    private String localidad;
    @NotBlank(message = "Debes ingresar una provincia")
    private String provincia;


    @Override
    public String toString() {
        return "Domicilio{" +
                "id=" + id +
                ", calle='" + calle + '\'' +
                ", numero=" + numero +
                ", localidad='" + localidad + '\'' +
                ", provincia='" + provincia + '\'' +
                '}';
    }
}
