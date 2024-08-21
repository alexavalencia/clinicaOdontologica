package com.digitalhouse.clinicaOdontologica.controller;

import com.digitalhouse.clinicaOdontologica.model.Paciente;
import com.digitalhouse.clinicaOdontologica.service.PacienteService;
import org.springframework.ui.Model;
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
    public Paciente buscarPorId( @PathVariable Integer id){
        return pacienteService.getPacienteById(id);

    }

    @GetMapping("/buscarTodos")
    public List<Paciente> buscarTodos(){
        return pacienteService.getAll();

    }

    @PostMapping("/agregar")
    public Paciente agregarPaciente(@RequestBody Paciente paciente){
        return pacienteService.savePaciente(paciente);
    }

    @PutMapping("/modificar")
    public String modificarPaciente(@RequestBody Paciente paciente){
        pacienteService.updatePaciente(paciente);
        return "El paciente fue modificado";
    }

    @DeleteMapping("/eliminar/{id}")
    public String eliminarPaciente(@PathVariable Integer id){
        pacienteService.deletePacienteById(id);
        return "El paciente fue eliminado";
    }
}
