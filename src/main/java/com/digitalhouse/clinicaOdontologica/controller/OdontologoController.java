package com.digitalhouse.clinicaOdontologica.controller;

import com.digitalhouse.clinicaOdontologica.entity.Odontologo;
import com.digitalhouse.clinicaOdontologica.service.IOdontologoService;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologo")
public class OdontologoController {

    private IOdontologoService odontologoService;

    public OdontologoController(IOdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id){
        Optional<Odontologo> odontologo = odontologoService.getOdontologoById(id);
        if(odontologo.isPresent()){
        return ResponseEntity.ok(odontologo.get());
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
        Optional<Odontologo> odontologoAModificar = odontologoService.getOdontologoById(odontologo.getId());
        if(odontologoAModificar.isPresent()){
            odontologoService.updateOdontologo(odontologo);
            return ResponseEntity.status(HttpStatus.OK).body("El odontologo fue actualizado");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El Odontologo no fue encontrado para modificarlo");
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarOdontologo(@PathVariable Integer id){
        Optional<Odontologo> odontogoAEliminar = odontologoService.getOdontologoById(id);
        if(odontogoAEliminar.isPresent()){
            odontologoService.deleteOdontologo(id);
            return ResponseEntity.status(HttpStatus.OK).body("El odontologo fue eliminado");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El Odontologo no fue encontrado para eliminarlo");
    }
    @GetMapping("/buscarPorMatricula")
    public ResponseEntity<?> buscarOdontologoPorNumeroMatricula(@RequestParam String numeroMatricula){
        Odontologo odontologo = odontologoService.getOdontologoByNumeroMatricula(numeroMatricula);
        if(odontologo!= null){
            return ResponseEntity.ok(odontologo);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron odontologos");
    }

    @GetMapping("/buscarPorApellido")
    public ResponseEntity<?> buscarOdontologosPorApellido(@RequestParam String apellido){
        List<Odontologo> odontologos = odontologoService.getByApellido(apellido);
        if(!odontologos.isEmpty()){
            return ResponseEntity.ok(odontologos);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron odontologos");
    }
}
