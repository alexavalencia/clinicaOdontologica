package com.digitalhouse.clinicaOdontologica.service;

import com.digitalhouse.clinicaOdontologica.dto.response.EspecialidadResponseDTO;
import com.digitalhouse.clinicaOdontologica.entity.Especialidad;
import com.digitalhouse.clinicaOdontologica.entity.Paciente;

import java.util.List;
import java.util.Optional;

public interface IEspecialidadService {

    EspecialidadResponseDTO saveEspecialidad(Especialidad especialidad);
    Optional<EspecialidadResponseDTO> getEspecialidadById(Integer id);
    List<EspecialidadResponseDTO> getAll();
}
