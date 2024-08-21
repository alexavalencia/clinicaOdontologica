package com.digitalhouse.clinicaOdontologica.controller;

import com.digitalhouse.clinicaOdontologica.model.Odontologo;
import com.digitalhouse.clinicaOdontologica.model.Paciente;
import com.digitalhouse.clinicaOdontologica.service.OdontologoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologo")
public class OdontologoController {

    private OdontologoService odontologoService;

    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @GetMapping("/buscar/{id}")
    public Odontologo buscarPorId(@PathVariable Integer id){
        return odontologoService.getOdontologoById(id);

    }

    @GetMapping("/buscarTodos")
    public List<Odontologo> buscarTodos(){
        return odontologoService.getAll();

    }

    @PostMapping("/agregar")
    public Odontologo agregarOdontologo(@RequestBody Odontologo odontologo){
        return odontologoService.saveOdontologo(odontologo);
    }

    @PutMapping("/modificar")
    public String modificarOdontologo(@RequestBody Odontologo odontologo){
        odontologoService.updateOdontologo(odontologo);
        return "El Odontologo fue modificado";
    }

    @DeleteMapping("/eliminar/{id}")
    public String eliminarOdontologo(@PathVariable Integer id){
        odontologoService.deleteOdontologo(id);
        return "El Odontologo fue eliminado";
    }

}
