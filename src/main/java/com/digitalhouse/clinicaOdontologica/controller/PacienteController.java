package com.digitalhouse.clinicaOdontologica.controller;

import com.digitalhouse.clinicaOdontologica.model.Paciente;
import com.digitalhouse.clinicaOdontologica.service.PacienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    private PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }


    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarPorId( @PathVariable Integer id){
        Paciente paciente = pacienteService.getPacienteById(id);
        if(paciente != null){
            return ResponseEntity.ok(paciente);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro el paciente") ;

    }

    @GetMapping("/buscarTodos")
    public ResponseEntity<?> buscarTodos(){
        List<Paciente> pacientes = pacienteService.getAll();
        if(!pacientes.isEmpty()){
            return ResponseEntity.ok(pacientes);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay pacientes ingresados") ;


    }

    @PostMapping("/agregar")
    public ResponseEntity<Paciente> agregarPaciente(@RequestBody Paciente paciente){
        return ResponseEntity.ok(pacienteService.savePaciente(paciente));
    }

    @PutMapping("/modificar")
    public ResponseEntity<String> modificarPaciente(@RequestBody Paciente paciente){
        Paciente pacienteActual = pacienteService.getPacienteById(paciente.getId());
        if(pacienteActual != null){
            pacienteService.updatePaciente(paciente);
            return ResponseEntity.status(HttpStatus.OK).body("Paciente fue actualizado");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro el paciente") ;

    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarPaciente(@PathVariable Integer id){
        Paciente pacienteActual = pacienteService.getPacienteById(id);
        if(pacienteActual != null){
            pacienteService.deletePacienteById(id);
            return  ResponseEntity.status(HttpStatus.OK).body("Paciente fue eliminado");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro el paciente") ;


    }
}
