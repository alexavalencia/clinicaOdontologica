package com.digitalhouse.clinicaOdontologica.controller;

import com.digitalhouse.clinicaOdontologica.model.Turno;
import com.digitalhouse.clinicaOdontologica.service.PacienteService;
import com.digitalhouse.clinicaOdontologica.service.TurnoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    private TurnoService turnoService;

    public TurnoController(TurnoService turnoService) {
        this.turnoService = turnoService;
    }

    @PostMapping("/agregar")
    public ResponseEntity<?> gurdarTurno(@RequestBody Turno turno){
        Turno turno1 = turnoService.saveTurno(turno);
        if (turno1!= null){
            return ResponseEntity.ok(turno1);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El paciente o el odontologo no fueron encontrados");
        }

    }
    @GetMapping("/buscarTodos")
    public ResponseEntity<List<Turno>> buscarTodos(){
        return ResponseEntity.ok(turnoService.getAll());
    }

}
