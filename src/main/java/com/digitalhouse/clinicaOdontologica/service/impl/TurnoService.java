package com.digitalhouse.clinicaOdontologica.service.impl;


import com.digitalhouse.clinicaOdontologica.entity.Odontologo;
import com.digitalhouse.clinicaOdontologica.entity.Paciente;
import com.digitalhouse.clinicaOdontologica.entity.Turno;
import com.digitalhouse.clinicaOdontologica.repository.ITurnoRepository;
import com.digitalhouse.clinicaOdontologica.service.ITurnoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoService implements ITurnoService {

    private ITurnoRepository turnoRepository;
    private PacienteService pacienteService;
    private OdontologoService odontologoService;

    public TurnoService(ITurnoRepository turnoRepository, PacienteService pacienteService, OdontologoService odontologoService) {
        this.turnoRepository = turnoRepository;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }

    @Override
    public Turno saveTurno(Turno turno){
        Optional<Paciente> paciente = pacienteService.getPacienteById(turno.getPaciente().getId());
        Optional<Odontologo> odontologo = odontologoService.getOdontologoById(turno.getOdontologo().getId());
        if(paciente.isPresent() && odontologo.isPresent()){
            turno.setPaciente(paciente.get());
            turno.setOdontologo(odontologo.get());
            return turnoRepository.save(turno);
        }
        return null;
    }
    @Override
    public Optional<Turno> getTurnoById(Integer id){

        return turnoRepository.findById(id);
    }
    @Override
    public List<Turno> getAll(){
        return turnoRepository.findAll();
    }
    @Override
    public void updateTurno(Turno turno){
        Optional<Paciente> paciente = pacienteService.getPacienteById(turno.getPaciente().getId());
        Optional<Odontologo> odontologo = odontologoService.getOdontologoById(turno.getOdontologo().getId());
        if(paciente.isPresent() && odontologo.isPresent()){
            turno.setPaciente(paciente.get());
            turno.setOdontologo(odontologo.get());
            turnoRepository.save(turno);
        }
    }
    @Override
    public void deleteTurno(Integer id){
        turnoRepository.deleteById(id);
    }
}
