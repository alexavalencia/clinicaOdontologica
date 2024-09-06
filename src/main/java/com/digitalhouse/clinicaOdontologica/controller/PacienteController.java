package com.digitalhouse.clinicaOdontologica.controller;

import com.digitalhouse.clinicaOdontologica.entity.Paciente;
import com.digitalhouse.clinicaOdontologica.service.IPacienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    private IPacienteService pacienteService;

    public PacienteController(IPacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarPorId( @PathVariable Integer id){
        Optional<Paciente> paciente = pacienteService.getPacienteById(id);
        if(paciente.isPresent()){
            return ResponseEntity.ok(paciente.get());
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
    public ResponseEntity<Paciente> agregarPaciente(@Valid @RequestBody Paciente paciente){
        return ResponseEntity.ok(pacienteService.savePaciente(paciente));
    }

    @PutMapping("/modificar")
    public ResponseEntity<String> modificarPaciente(@Valid @RequestBody Paciente paciente){
        pacienteService.updatePaciente(paciente);
        return ResponseEntity.status(HttpStatus.OK).body("Paciente fue actualizado");
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarPaciente(@PathVariable Integer id){
            pacienteService.deletePacienteById(id);
            return  ResponseEntity.status(HttpStatus.OK).body("Paciente fue eliminado");

    }
}
