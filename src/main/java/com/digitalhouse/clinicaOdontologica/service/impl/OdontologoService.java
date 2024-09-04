package com.digitalhouse.clinicaOdontologica.service.impl;


import com.digitalhouse.clinicaOdontologica.entity.Odontologo;
import com.digitalhouse.clinicaOdontologica.exception.ResourceNotFoundException;
import com.digitalhouse.clinicaOdontologica.repository.IOdontologoRepository;
import com.digitalhouse.clinicaOdontologica.service.IOdontologoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService implements IOdontologoService {

    private final Logger LOGGER = LoggerFactory.getLogger(OdontologoService.class);
    private IOdontologoRepository odontologoRepository;

    public OdontologoService(IOdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    @Override
    public Odontologo saveOdontologo(Odontologo odontologo) {
        Odontologo odontologoDB = null;
        odontologoDB =odontologoRepository.save(odontologo);
        if (odontologoDB!=null){
            LOGGER.info("Odontologo guardado " + odontologoDB.toString());
        }else{
            LOGGER.info("No se guardo ningun odontologo");
            throw new ResourceNotFoundException("El odontologo no fue guardado");

        }

        return odontologoDB ;
    }

    @Override
    public Optional<Odontologo> getOdontologoById(int id) {
        Optional<Odontologo> odontologoDB = null;
        odontologoDB =odontologoRepository.findById(id);
        if (odontologoDB.isPresent()){
            LOGGER.info("Odontologo encontrado " + odontologoDB.get().toString());
        }else{
            LOGGER.info("No se encontro ningun odontologo ");
            throw new ResourceNotFoundException("El odontologo con id " + id +" no fue encontrado");

        }
        return odontologoDB ;
    }

    @Override
    public List<Odontologo> getAll() {
        List<Odontologo> odontologosDB = null;
        odontologosDB =odontologoRepository.findAll();
        if (!odontologosDB.isEmpty()){
            LOGGER.info("Odontologos encontrados " + odontologosDB);
        }else{
            LOGGER.info("No se encontro ningun odontologo");
            throw new ResourceNotFoundException("No se encontro ningun odontologo");

        }

        return odontologosDB ;
    }

    @Override
    public void updateOdontologo(Odontologo odontologo) {
        Optional<Odontologo> odontologoDB = odontologoRepository.findById(odontologo.getId());
        if (odontologoDB.isPresent()){
            odontologoRepository.save(odontologo);
            LOGGER.info("Odontologo fue actualizado");
        }else {
            LOGGER.info("No se encontro el odontologo para actualizar");
            throw new ResourceNotFoundException("El odontologo con id " + odontologo.getId() +" no fue encontrado para actualizar");
        }

    }

    @Override
    public void deleteOdontologo(Integer id) {
        Optional<Odontologo> odontologoDB = odontologoRepository.findById(id);
        if (odontologoDB.isPresent()){
            odontologoRepository.deleteById(id);
            LOGGER.info("Odontologo fue eliminado");
        }else {
            LOGGER.info("No se encontro el odontologo para eliminar");
            throw new ResourceNotFoundException("El odontologo con id " + id +" no fue encontrado para eliminar");
        }

    }

    @Override
    public Odontologo getOdontologoByNumeroMatricula(String numeroMatricula) {
        Odontologo odontologoDB = null;
        odontologoDB =odontologoRepository.findByNumeroMatricula(numeroMatricula);
        if (odontologoDB!=null){
            LOGGER.info("Odontologo encontrado" + odontologoDB.toString());
        }else{
            LOGGER.info("No se encontro ningun odontologo ");
            throw new ResourceNotFoundException("El odontologo con matriucla " + numeroMatricula +" no fue encontrado");
        }
        return odontologoDB ;
    }

    @Override
    public List<Odontologo> getByApellido(String apellido) {
        List<Odontologo> odontologosDB = null;
        odontologosDB =odontologoRepository.findByApellido(apellido);
        if (!odontologosDB.isEmpty()){
            LOGGER.info("Se encontraron odontologos");
        }else{
            LOGGER.info("No se encontro ningun odontologo ");
            throw new ResourceNotFoundException("El odontologo con apellido " + apellido +" no fue encontrado");

        }
        return odontologosDB ;
    }
}
