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
        return  ResponseEntity.status(HttpStatus.OK).body(odontologo.get());

    }

    @GetMapping("/buscarTodos")
    public ResponseEntity<?> buscarTodos(){
        List<Odontologo> odontologos = odontologoService.getAll();
        return  ResponseEntity.status(HttpStatus.OK).body(odontologos);
    }

    @PostMapping("/agregar")
    public ResponseEntity<Odontologo>agregarOdontologo(@RequestBody Odontologo odontologo){
        return ResponseEntity.status(HttpStatus.OK).body(odontologoService.saveOdontologo(odontologo));
    }

    @PutMapping("/modificar")
    public ResponseEntity<String> modificarOdontologo(@RequestBody Odontologo odontologo){
        odontologoService.updateOdontologo(odontologo);
        return ResponseEntity.status(HttpStatus.OK).body("El odontologo fue actualizado");

    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarOdontologo(@PathVariable Integer id){
        odontologoService.deleteOdontologo(id);
        return ResponseEntity.status(HttpStatus.OK).body("El odontologo fue eliminado");
    }
    @GetMapping("/buscarPorMatricula")
    public ResponseEntity<?> buscarOdontologoPorNumeroMatricula(@RequestParam String numeroMatricula){
        Odontologo odontologo = odontologoService.getOdontologoByNumeroMatricula(numeroMatricula);
        return ResponseEntity.status(HttpStatus.OK).body(odontologo);

    }

    @GetMapping("/buscarPorApellido")
    public ResponseEntity<?> buscarOdontologosPorApellido(@RequestParam String apellido){
        List<Odontologo> odontologos = odontologoService.getByApellido(apellido);
        return ResponseEntity.status(HttpStatus.OK).body(odontologos);
    }
}
