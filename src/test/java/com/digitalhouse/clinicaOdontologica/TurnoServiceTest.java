package com.digitalhouse.clinicaOdontologica;

import com.digitalhouse.clinicaOdontologica.dto.request.TurnoRequestDTO;
import com.digitalhouse.clinicaOdontologica.dto.response.TurnoResponseDTO;
import com.digitalhouse.clinicaOdontologica.entity.Domicilio;
import com.digitalhouse.clinicaOdontologica.entity.Odontologo;
import com.digitalhouse.clinicaOdontologica.entity.Paciente;
import com.digitalhouse.clinicaOdontologica.entity.Turno;
import com.digitalhouse.clinicaOdontologica.service.impl.OdontologoService;
import com.digitalhouse.clinicaOdontologica.service.impl.PacienteService;
import com.digitalhouse.clinicaOdontologica.service.impl.TurnoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Transactional
class TurnoServiceTest {
    @Autowired
    TurnoService turnoService;
    TurnoRequestDTO turno;
    TurnoResponseDTO turnoDesdeDb;
    @Autowired
    PacienteService pacienteService;
    Paciente paciente;
    Paciente pacienteDesdeDb;
    @Autowired
    OdontologoService odontologoService;
    Odontologo odontologo;
    Odontologo odontologoDesdeDb;

    @BeforeEach
    void createTurno(){
        Domicilio domicilio = new Domicilio(null,"Falsa]",123456,"Cipolleti","Rio. Negro");
        Paciente paciente = new Paciente();
        paciente.setApellido("Lopez");
        paciente.setNombre("Paola");
        paciente.setDni("4501241");
        paciente.setFechaIngreso(LocalDate.of(2024,07,20));
        paciente.setDomicilio(domicilio);
        pacienteDesdeDb =pacienteService.savePaciente(paciente);
        odontologo = new Odontologo();
        odontologo.setApellido("Suarez");
        odontologo.setNombre("Luis");
        odontologo.setNumeroMatricula("pe103sa");
        odontologoDesdeDb =odontologoService.saveOdontologo(odontologo);
        turno = new TurnoRequestDTO();
        turno.setFecha("2024-05-20");
        turno.setOdontologo_id(odontologoDesdeDb.getId());
        turno.setPaciente_id(pacienteDesdeDb.getId());
        turnoDesdeDb = turnoService.saveTurno(turno);
    }
    @Test
    @DisplayName("Turno se guarda en la db")
    void test1(){
        assertNotNull(turnoDesdeDb.getId());
    }

    @Test
    @DisplayName("Obtener turno con id")
    void test2(){
        Integer id = turnoDesdeDb.getId();
        TurnoResponseDTO turnoResponseDTO=turnoService.getTurnoById(id).get();
        assertEquals(id,turnoResponseDTO.getId());
    }

    @Test
    @DisplayName("Obtener todos los turnos")
    void test3(){
        assertTrue(!turnoService.getAll().isEmpty());
    }
}
