package com.digitalhouse.clinicaOdontologica;

import com.digitalhouse.clinicaOdontologica.dao.imp.OdontologoDaoH2;
import com.digitalhouse.clinicaOdontologica.db.H2Connection;
import com.digitalhouse.clinicaOdontologica.model.Odontologo;
import com.digitalhouse.clinicaOdontologica.service.OdontologoService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OdontologoServiceTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(OdontologoServiceTest.class);
    private OdontologoService odontologoService = new OdontologoService(new OdontologoDaoH2());

    @BeforeAll
    static void creatTable(){
        H2Connection.crearTablas();
    }

    @Test
    @DisplayName("Guardar Odontologo")
    void test1(){
        Odontologo odontologo = new Odontologo("123458","Camila","Valencia");
        Odontologo odontologoGuardado = odontologoService.saveOdontologo(odontologo);
        assertNotNull(odontologoGuardado.getId());

    }

    @Test
    @DisplayName("Obtener odontologo por id")
    void test2(){
        Odontologo odontologoGuardado = odontologoService.getOdontologoById(2);
        assertEquals(2, odontologoGuardado.getId());
        assertEquals("GIRALDO", odontologoGuardado.getApellido());

    }


    @Test
    @DisplayName("Obtener todos los odontologos")
    void test4(){
        List<Odontologo> odontologoGuardados = odontologoService.getAll();
        assertTrue(odontologoGuardados.size()>0);


    }

}