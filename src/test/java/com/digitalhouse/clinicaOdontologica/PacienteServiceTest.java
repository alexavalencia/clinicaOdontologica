package com.digitalhouse.clinicaOdontologica;

import com.digitalhouse.clinicaOdontologica.dao.imp.PacienteDaoH2;
import com.digitalhouse.clinicaOdontologica.db.H2Connection;
import com.digitalhouse.clinicaOdontologica.model.Domicilio;
import com.digitalhouse.clinicaOdontologica.model.Paciente;
import com.digitalhouse.clinicaOdontologica.service.PacienteService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PacienteServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(PacienteServiceTest.class);
    private PacienteService pacienteService = new PacienteService(new PacienteDaoH2());

    @BeforeAll
    static void creatTable(){
        H2Connection.crearTablas();
    }

    @Test
    @DisplayName("Paciente se guarda en la db con su domicilio")
    void test1(){

        Paciente paciente = new Paciente("Aguirre","Juan","1214721071", LocalDate.of(2024,07,16),
                new Domicilio("Falsa]",123456,"Cipolleti","Rio. Negro"));
        Paciente pacienteActual=pacienteService.savePaciente(paciente);
        assertNotNull(pacienteActual.getId());
    }

    @Test
    @DisplayName("Obtener paciente con id 1")
    void test2(){

        Paciente paciente = null;
        Paciente pacienteActual=pacienteService.getPacienteById(1);
        assertEquals(1,pacienteActual.getId());
    }

    @Test
    @DisplayName("Obtener totod los pacientes")
    void test3(){
        assertTrue(pacienteService.getAll().size()>0);
    }

}