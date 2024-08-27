package com.digitalhouse.clinicaOdontologica.service;

import com.digitalhouse.clinicaOdontologica.entity.Paciente;

import java.util.List;
import java.util.Optional;

public interface IPacienteService {

     Paciente savePaciente(Paciente paciente);
     Optional<Paciente> getPacienteById(Integer id);
     List<Paciente> getAll();
     void updatePaciente(Paciente paciente);
     void deletePacienteById(Integer id);
}
