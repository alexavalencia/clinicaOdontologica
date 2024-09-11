package com.digitalhouse.clinicaOdontologica.service.impl;


import com.digitalhouse.clinicaOdontologica.entity.Paciente;
import com.digitalhouse.clinicaOdontologica.exception.ResourceNotFoundException;
import com.digitalhouse.clinicaOdontologica.repository.IPacienteRepository;
import com.digitalhouse.clinicaOdontologica.service.IPacienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class PacienteService implements IPacienteService {

    private final Logger LOGGER = LoggerFactory.getLogger(PacienteService.class);

    private IPacienteRepository pacienteRepository;

    public PacienteService(IPacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public Paciente savePaciente(Paciente paciente) {
        Paciente pacienteDB= null;
        pacienteDB=pacienteRepository.save(paciente);
        if(pacienteDB == null){
            LOGGER.info("No se guardo ningun paciente");
            throw new ResourceNotFoundException("El paciente no fue guardado");
        }else{
            LOGGER.info("Paciente  guardado " + pacienteDB.toString());
        }
        return pacienteDB;
    }

    @Override
    public Optional<Paciente> getPacienteById(Integer id) {
        Optional<Paciente> paciente = pacienteRepository.findById(id);
        LOGGER.info("Paciente encontrado " + paciente.get().toString() );
        return paciente;
    }

    @Override
    public List<Paciente> getAll() {
        List<Paciente> pacientes = pacienteRepository.findAll();
        LOGGER.info("Pacientes encontrados " + pacientes);
        return pacientes ;
    }

    @Override
    public void updatePaciente(Paciente paciente) {
        Optional<Paciente>pacienteFound= pacienteRepository.findById(paciente.getId());
        if(pacienteFound.isPresent()){
            LOGGER.info("Paciente fue actualizado");
            pacienteRepository.save(paciente);

        }else{
            LOGGER.info("El paciente "+ paciente.getId() +" no fue encontrado");
            throw new ResourceNotFoundException("El paciente "+ paciente.getId() +" no fue encontrado");
        }

    }

    @Override
    public void deletePacienteById(Integer id) {
        Optional<Paciente>pacienteFound= pacienteRepository.findById(id);
        if(pacienteFound.isPresent()){
            LOGGER.info("Paciente fue eliminado");
            pacienteRepository.deleteById(id);
        }else{
            LOGGER.info("El paciente "+ id +" no fue encontrado");
            throw new ResourceNotFoundException("El paciente "+ id +" no fue encontrado");
        }
    }
}
