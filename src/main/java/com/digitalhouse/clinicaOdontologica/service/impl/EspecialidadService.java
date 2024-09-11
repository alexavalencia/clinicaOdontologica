package com.digitalhouse.clinicaOdontologica.service.impl;

import com.digitalhouse.clinicaOdontologica.dto.response.EspecialidadResponseDTO;
import com.digitalhouse.clinicaOdontologica.entity.Especialidad;
import com.digitalhouse.clinicaOdontologica.entity.Paciente;
import com.digitalhouse.clinicaOdontologica.exception.ResourceNotFoundException;
import com.digitalhouse.clinicaOdontologica.repository.IEspecialidadRepository;
import com.digitalhouse.clinicaOdontologica.service.IEspecialidadService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EspecialidadService implements IEspecialidadService {

    private final Logger LOGGER = LoggerFactory.getLogger(EspecialidadService.class);
    @Autowired
    private IEspecialidadRepository especialidadRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public EspecialidadResponseDTO saveEspecialidad(Especialidad especialidad) {
        Especialidad especialidadDB= null;
        especialidadDB=especialidadRepository.save(especialidad);
        if(especialidadDB == null){
            LOGGER.info("No se guardo ninguna especialidad");
            throw new ResourceNotFoundException("La especialidad  no fue guardado");
        }else{
            LOGGER.info("Especialidad  guardada " + especialidadDB.toString());
        }
        return convertirAResponse(especialidadDB);
    }

    @Override
    public Optional<EspecialidadResponseDTO> getEspecialidadById(Integer id) {
        Optional<Especialidad> especialidad = especialidadRepository.findById(id);
        LOGGER.info("Especialidad encontrada " + especialidad.get().toString() );
        return Optional.ofNullable(convertirAResponse(especialidad.get()));
    }

    @Override
    public List<EspecialidadResponseDTO> getAll() {
        return especialidadRepository.findAll().stream().map(x-> convertirAResponse(x)).toList();
    }

    EspecialidadResponseDTO convertirAResponse(Especialidad especialidad){
        modelMapper.typeMap(Especialidad.class, EspecialidadResponseDTO.class).addMappings(
                mapper -> mapper.map(src -> src.getOdontologos(), EspecialidadResponseDTO::setOdontologos)
        );
        return modelMapper.map(especialidad, EspecialidadResponseDTO.class);
    }
}
