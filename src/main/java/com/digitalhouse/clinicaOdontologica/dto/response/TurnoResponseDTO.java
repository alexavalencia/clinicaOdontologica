package com.digitalhouse.clinicaOdontologica.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TurnoResponseDTO {
    private Integer id;
    //paciente
    private PacienteResponseDTO pacienteResponseDTO;

    //odontologo
    private OdontologoResponseDTO odontologoResponseDTO;
    private String fecha;
}
