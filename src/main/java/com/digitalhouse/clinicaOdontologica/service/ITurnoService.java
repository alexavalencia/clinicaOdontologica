package com.digitalhouse.clinicaOdontologica.service;

import com.digitalhouse.clinicaOdontologica.dto.request.TurnoRequestDTO;
import com.digitalhouse.clinicaOdontologica.dto.request.TurnoUpdateDTO;
import com.digitalhouse.clinicaOdontologica.dto.response.TurnoResponseDTO;
import com.digitalhouse.clinicaOdontologica.entity.Odontologo;
import com.digitalhouse.clinicaOdontologica.entity.Paciente;
import com.digitalhouse.clinicaOdontologica.entity.Turno;

import java.util.List;
import java.util.Optional;

public interface ITurnoService {

     TurnoResponseDTO saveTurno(TurnoRequestDTO turnoRequestDTO);
     Optional<TurnoResponseDTO> getTurnoById(Integer id);
     List<TurnoResponseDTO> getAll();
     void updateTurno(TurnoUpdateDTO turnoUpdate);
     void deleteTurno(Integer id);
}
