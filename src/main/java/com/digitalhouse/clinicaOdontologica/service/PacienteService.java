package com.digitalhouse.clinicaOdontologica.service;

import com.digitalhouse.clinicaOdontologica.dao.IDao;
import com.digitalhouse.clinicaOdontologica.model.Paciente;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class PacienteService {

    private IDao<Paciente> pacienteIDao;

    public PacienteService(IDao<Paciente> pacienteIDao) {
        this.pacienteIDao = pacienteIDao;
    }

    public Paciente savePaciente(Paciente paciente){
        return pacienteIDao.save(paciente);
    }
    public  Paciente getPacienteById(Integer id){
        return  pacienteIDao.getById(id);
    }
    public List<Paciente> getAll(){
        return pacienteIDao.getAll();
    }

    public void updatePaciente(Paciente paciente){
        pacienteIDao.update(paciente);
    }

    public void deletePacienteById(Integer id){
        pacienteIDao.delete(id);
    }
}
