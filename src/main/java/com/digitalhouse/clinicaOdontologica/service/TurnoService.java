package com.digitalhouse.clinicaOdontologica.service;

import com.digitalhouse.clinicaOdontologica.dao.IDao;
import com.digitalhouse.clinicaOdontologica.model.Odontologo;
import com.digitalhouse.clinicaOdontologica.model.Paciente;
import com.digitalhouse.clinicaOdontologica.model.Turno;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService {

    private IDao<Turno> turnoIDao;
    private PacienteService pacienteService;
    private OdontologoService odontologoService;

    public TurnoService(IDao<Turno> turnoIDao, PacienteService pacienteService, OdontologoService odontologoService) {
        this.turnoIDao = turnoIDao;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }

    public Turno saveTurno(Turno turno){
        Paciente paciente = pacienteService.getPacienteById(turno.getPaciente().getId());
        Odontologo odontologo = odontologoService.getOdontologoById(turno.getOdontologo().getId());
        Turno turnoRetorno= null;
        if(paciente!= null && odontologo!=null){
            turno.setPaciente(paciente);
            turno.setOdontologo(odontologo);
            return turnoIDao.save(turno);
        }
        return null;
    }
    public Turno getTurnoById(Integer id){
        return turnoIDao.getById(id);
    }

    public List<Turno> getAll(){
        return turnoIDao.getAll();
    }

}
