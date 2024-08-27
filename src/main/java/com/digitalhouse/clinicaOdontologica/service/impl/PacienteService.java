package com.digitalhouse.clinicaOdontologica.service.impl;


import com.digitalhouse.clinicaOdontologica.entity.Paciente;
import com.digitalhouse.clinicaOdontologica.repository.IPacienteRepository;
import com.digitalhouse.clinicaOdontologica.service.IPacienteService;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class PacienteService implements IPacienteService {

    private IPacienteRepository pacienteRepository;

    public PacienteService(IPacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public Paciente savePaciente(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    @Override
    public Optional<Paciente> getPacienteById(Integer id) {
        return pacienteRepository.findById(id);
    }

    @Override
    public List<Paciente> getAll() {
        return pacienteRepository.findAll();
    }

    @Override
    public void updatePaciente(Paciente paciente) {
        pacienteRepository.save(paciente);
    }

    @Override
    public void deletePacienteById(Integer id) {
        pacienteRepository.deleteById(id);
    }
}
