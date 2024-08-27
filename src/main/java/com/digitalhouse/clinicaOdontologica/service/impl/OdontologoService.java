package com.digitalhouse.clinicaOdontologica.service.impl;


import com.digitalhouse.clinicaOdontologica.entity.Odontologo;
import com.digitalhouse.clinicaOdontologica.repository.IOdontologoRepository;
import com.digitalhouse.clinicaOdontologica.service.IOdontologoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService implements IOdontologoService {

    private IOdontologoRepository odontologoRepository;

    public OdontologoService(IOdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    @Override
    public Odontologo saveOdontologo(Odontologo odontologo) {
        return odontologoRepository.save(odontologo);
    }

    @Override
    public Optional<Odontologo> getOdontologoById(int id) {
        return odontologoRepository.findById(id);
    }

    @Override
    public List<Odontologo> getAll() {
        return odontologoRepository.findAll();
    }

    @Override
    public void updateOdontologo(Odontologo odontologo) {
        odontologoRepository.save(odontologo);
    }

    @Override
    public void deleteOdontologo(Integer id) {
        odontologoRepository.deleteById(id);
    }
}
