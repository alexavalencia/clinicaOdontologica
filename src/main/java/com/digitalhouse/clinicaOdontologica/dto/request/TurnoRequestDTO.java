package com.digitalhouse.clinicaOdontologica.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TurnoRequestDTO {
    @NotNull(message = "Debes ingresar un id de paciente")
    private Integer paciente_id;
    @NotNull(message = "Debes ingresar un id de odontologo")
    private Integer odontologo_id;
    @NotBlank(message = "Debes ingresar una fecha")
    private String fecha;
}
