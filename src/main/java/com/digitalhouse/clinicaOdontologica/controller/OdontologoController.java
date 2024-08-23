package com.digitalhouse.clinicaOdontologica.controller;

import com.digitalhouse.clinicaOdontologica.model.Odontologo;
import com.digitalhouse.clinicaOdontologica.service.OdontologoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id){
        Odontologo odontologo = odontologoService.getOdontologoById(id);
        if(odontologo != null){
        return ResponseEntity.ok(odontologo);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El odontologo no fue encontrado");
    }

    @GetMapping("/buscarTodos")
    public ResponseEntity<?> buscarTodos(){
        List<Odontologo> odontologos = odontologoService.getAll();
        if(odontologos != null){
            return ResponseEntity.ok(odontologos);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron odontologos");
    }

    @PostMapping("/agregar")
    public ResponseEntity<Odontologo>agregarOdontologo(@RequestBody Odontologo odontologo){
        return ResponseEntity.ok(odontologoService.saveOdontologo(odontologo));
    }

    @PutMapping("/modificar")
    public ResponseEntity<String> modificarOdontologo(@RequestBody Odontologo odontologo){
        Odontologo odontologoAModificar = odontologoService.getOdontologoById(odontologo.getId());
        if(odontologoAModificar != null){
        odontologoService.updateOdontologo(odontologo);
        return ResponseEntity.status(HttpStatus.OK).body("El odontologo fue actualizado");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El Odontologo no fue encontrado para modificarlo");
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarOdontologo(@PathVariable Integer id){
        Odontologo odontogoAEliminar = odontologoService.getOdontologoById(id);
        if(odontogoAEliminar != null){
            odontologoService.deleteOdontologo(id);
            return ResponseEntity.status(HttpStatus.OK).body("El odontologo fue eliminado");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El Odontologo no fue encontrado para eliminarlo");
    }
}
