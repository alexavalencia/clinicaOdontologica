package com.digitalhouse.clinicaOdontologica;


import com.digitalhouse.clinicaOdontologica.entity.Domicilio;
import com.digitalhouse.clinicaOdontologica.entity.Odontologo;
import com.digitalhouse.clinicaOdontologica.entity.Paciente;
import com.digitalhouse.clinicaOdontologica.service.impl.OdontologoService;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Transactional
class OdontologoServiceTest {
    @Autowired
    OdontologoService odontologoService;
    Odontologo odontologo;
    Odontologo odontologoDesdeDb;
    @BeforeEach
    void createOdontologo(){
        odontologo = new Odontologo();
        odontologo.setApellido("Suarez");
        odontologo.setNombre("Luis");
        odontologo.setNumeroMatricula("pe103sa");
        odontologoDesdeDb =odontologoService.saveOdontologo(odontologo);
    }

    @Test
    @DisplayName("Guardar Odontologo")
    void test1(){
        assertNotNull(odontologoDesdeDb.getId());
    }

    @Test
    @DisplayName("Obtener odontologo por id")
    void test2(){
        Integer id = odontologoDesdeDb.getId();
        Odontologo odontologoActual=odontologoService.getOdontologoById(id).get();
        assertEquals(id,odontologoActual.getId());

    }

    @Test
    @DisplayName("Obtener todos los odontologos")
    void test4(){
        assertTrue(!odontologoService.getAll().isEmpty());
    }

}