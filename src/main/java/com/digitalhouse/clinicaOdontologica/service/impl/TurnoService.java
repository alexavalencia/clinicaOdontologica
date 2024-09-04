package com.digitalhouse.clinicaOdontologica.service.impl;


import com.digitalhouse.clinicaOdontologica.dto.request.TurnoRequestDTO;
import com.digitalhouse.clinicaOdontologica.dto.request.TurnoUpdateDTO;
import com.digitalhouse.clinicaOdontologica.dto.response.OdontologoResponseDTO;
import com.digitalhouse.clinicaOdontologica.dto.response.PacienteResponseDTO;
import com.digitalhouse.clinicaOdontologica.dto.response.TurnoResponseDTO;
import com.digitalhouse.clinicaOdontologica.entity.Odontologo;
import com.digitalhouse.clinicaOdontologica.entity.Paciente;
import com.digitalhouse.clinicaOdontologica.entity.Turno;
import com.digitalhouse.clinicaOdontologica.exception.BadRequestException;
import com.digitalhouse.clinicaOdontologica.exception.ResourceNotFoundException;
import com.digitalhouse.clinicaOdontologica.repository.ITurnoRepository;
import com.digitalhouse.clinicaOdontologica.service.ITurnoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService implements ITurnoService {

    private final Logger LOGGER = LoggerFactory.getLogger(Turno.class);

    private ITurnoRepository turnoRepository;
    private PacienteService pacienteService;
    private OdontologoService odontologoService;

    public TurnoService(ITurnoRepository turnoRepository, PacienteService pacienteService, OdontologoService odontologoService) {
        this.turnoRepository = turnoRepository;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }

    @Override
    public TurnoResponseDTO saveTurno(TurnoRequestDTO turnoRequestDTO){
        try {
            Optional<Paciente> paciente = pacienteService.getPacienteById(turnoRequestDTO.getPaciente_id());
            Optional<Odontologo> odontologo = odontologoService.getOdontologoById(turnoRequestDTO.getOdontologo_id());
            Turno turno = new Turno();
            Turno turnoDesdeDB = null;
            TurnoResponseDTO turnoARetornar = null;
            //mappear el turnoRequestDto a Turno
            turno.setPaciente(paciente.get());
            turno.setOdontologo(odontologo.get());
            turno.setFecha(LocalDate.parse(turnoRequestDTO.getFecha()));
            // voy a persistir el turno
            turnoDesdeDB = turnoRepository.save(turno);
            // mappear el turnoDesdeDB a TurnoResponseDTO
            turnoARetornar = mapperTurnoResponseDto(turnoDesdeDB);
            LOGGER.info("Turno fue  guardado " + turnoARetornar.toString());
            return turnoARetornar;
        }catch (ResourceNotFoundException e){
            LOGGER.info("El paciente con id " + turnoRequestDTO.getPaciente_id() + " o el odontologo con id " + turnoRequestDTO.getOdontologo_id() + " no fue encontrado");
            throw new BadRequestException("El paciente con id " + turnoRequestDTO.getPaciente_id() + " o el odontologo con id " + turnoRequestDTO.getOdontologo_id() + " no fue encontrado");
        }
    }
    @Override
    public Optional<TurnoResponseDTO> getTurnoById(Integer id){
        Optional<Turno> turnoDesdeDB = turnoRepository.findById(id);
        TurnoResponseDTO turnoResponseDTO = null;
        if(turnoDesdeDB.isPresent()){
            turnoResponseDTO = mapperTurnoResponseDto(turnoDesdeDB.get());
        }
        LOGGER.info("Turno encontrado " + turnoDesdeDB.get().toString());
        return Optional.ofNullable(turnoResponseDTO);
    }
    @Override
    public List<TurnoResponseDTO> getAll(){
        List<Turno> turnos = turnoRepository.findAll();
        List<TurnoResponseDTO> turnosRespuesta = new ArrayList<>();
        for(Turno t: turnos){
            turnosRespuesta.add(mapperTurnoResponseDto(t));
        }
        LOGGER.info("Turnos encontrados " + turnosRespuesta);
        return turnosRespuesta;
    }
    @Override
    public void updateTurno(TurnoUpdateDTO turnoUpdate){
        try{
            Optional<Paciente> paciente = pacienteService.getPacienteById(turnoUpdate.getPaciente_id());
            Optional<Odontologo> odontologo = odontologoService.getOdontologoById(turnoUpdate.getOdontologo_id());
            Turno turno = new Turno();
            Turno turnoDesdeDB = null;
            TurnoResponseDTO turnoARetornar = null;
            turno.setId(turnoUpdate.getId());
            turno.setPaciente(paciente.get());
            turno.setOdontologo(odontologo.get());
            turno.setFecha(LocalDate.parse(turnoUpdate.getFecha()));
            turnoRepository.save(turno);
            LOGGER.info("Turno fue actualizado");
        }catch (ResourceNotFoundException e){
            LOGGER.info("El paciente con id " + turnoUpdate.getPaciente_id() + " o el odontologo con id " + turnoUpdate.getOdontologo_id() + " no fue encontrado");
            throw new BadRequestException("El paciente con id " + turnoUpdate.getPaciente_id() + " o el odontologo con id " + turnoUpdate.getOdontologo_id() + " no fue encontrado");
        }
    }
    @Override
    public void deleteTurno(Integer id){
        Optional<Turno>turnoFound= turnoRepository.findById(id);
        if(turnoFound.isPresent()){
            LOGGER.info("Turno fue eliminado");
            turnoRepository.deleteById(id);
        }else{
            LOGGER.info("El turno con id  "+ id +" no fue encontrado");
            throw  new ResourceNotFoundException("El turno con id  "+ id +" no fue encontrado");
        }
    }

    private TurnoResponseDTO mapperTurnoResponseDto(Turno turnoDesdeDB){
        OdontologoResponseDTO odontologoResponseDTO = new OdontologoResponseDTO(
                turnoDesdeDB.getOdontologo().getId(), turnoDesdeDB.getOdontologo().getNombre(),
                turnoDesdeDB.getOdontologo().getNumeroMatricula(),turnoDesdeDB.getOdontologo().getApellido()
        );
        PacienteResponseDTO pacienteResponseDTO = new PacienteResponseDTO(
                turnoDesdeDB.getPaciente().getId(), turnoDesdeDB.getPaciente().getNombre(),
                turnoDesdeDB.getPaciente().getApellido(), turnoDesdeDB.getPaciente().getDni()
        );
        TurnoResponseDTO turnoARetornar = new TurnoResponseDTO(
                turnoDesdeDB.getId(), pacienteResponseDTO, odontologoResponseDTO,
                turnoDesdeDB.getFecha().toString()
        );
        return turnoARetornar;
    }
}
