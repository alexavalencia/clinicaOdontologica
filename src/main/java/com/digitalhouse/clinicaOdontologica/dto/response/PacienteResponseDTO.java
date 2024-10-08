package com.digitalhouse.clinicaOdontologica.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PacienteResponseDTO {
    private Integer id;
    private String nombre;
    private String apellido;
    private String dni;
}
