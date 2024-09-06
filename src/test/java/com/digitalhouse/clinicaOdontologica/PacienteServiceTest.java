package com.digitalhouse.clinicaOdontologica;


import com.digitalhouse.clinicaOdontologica.entity.Domicilio;
import com.digitalhouse.clinicaOdontologica.entity.Paciente;
import com.digitalhouse.clinicaOdontologica.service.impl.PacienteService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Transactional
class PacienteServiceTest {
    @Autowired
    PacienteService pacienteService;
    Paciente paciente;
    Paciente pacienteDesdeDb;

    @BeforeEach
    void createPaciente(){
        Domicilio domicilio = new Domicilio(null,"Falsa]",123456,"Cipolleti","Rio. Negro");
        Paciente paciente = new Paciente();
        paciente.setApellido("Lopez");
        paciente.setNombre("Paola");
        paciente.setDni("4501241");
        paciente.setFechaIngreso(LocalDate.of(2024,07,20));
        paciente.setDomicilio(domicilio);
        pacienteDesdeDb =pacienteService.savePaciente(paciente);
    }
    @Test
    @DisplayName("Paciente se guarda en la db con su domicilio")
    void test1(){
        assertNotNull(pacienteDesdeDb.getId());
    }

    @Test
    @DisplayName("Obtener paciente con id")
    void test2(){
        Integer id = pacienteDesdeDb.getId();
        Paciente pacienteActual=pacienteService.getPacienteById(id).get();
        assertEquals(id,pacienteActual.getId());
    }

    @Test
    @DisplayName("Obtener todos los pacientes")
    void test3(){
        assertTrue(!pacienteService.getAll().isEmpty());
    }
}