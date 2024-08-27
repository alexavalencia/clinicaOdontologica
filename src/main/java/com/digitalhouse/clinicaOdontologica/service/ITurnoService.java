package com.digitalhouse.clinicaOdontologica.service;

import com.digitalhouse.clinicaOdontologica.entity.Odontologo;
import com.digitalhouse.clinicaOdontologica.entity.Paciente;
import com.digitalhouse.clinicaOdontologica.entity.Turno;

import java.util.List;
import java.util.Optional;

public interface ITurnoService {

     Turno saveTurno(Turno turno);
     Optional<Turno> getTurnoById(Integer id);
     List<Turno> getAll();
     void updateTurno(Turno turno);
     void deleteTurno(Integer id);
}
