package com.digitalhouse.clinicaOdontologica.service;

import com.digitalhouse.clinicaOdontologica.entity.Odontologo;

import java.util.List;
import java.util.Optional;

public interface IOdontologoService {
     Odontologo saveOdontologo(Odontologo odontologo);
     Optional<Odontologo> getOdontologoById(int  id);
     List<Odontologo> getAll();
     void updateOdontologo(Odontologo odontologo);
     void deleteOdontologo(Integer id);
     Odontologo getOdontologoByNumeroMatricula(String numeroMatricula);
     List<Odontologo> getByApellido(String apellido);
     void addEspecialidad(Integer id_odontologo,Integer id_especialidad);
}
