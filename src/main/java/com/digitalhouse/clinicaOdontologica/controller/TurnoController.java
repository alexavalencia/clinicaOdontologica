package com.digitalhouse.clinicaOdontologica.controller;

import com.digitalhouse.clinicaOdontologica.dto.request.TurnoRequestDTO;
import com.digitalhouse.clinicaOdontologica.dto.request.TurnoUpdateDTO;
import com.digitalhouse.clinicaOdontologica.dto.response.TurnoResponseDTO;
import com.digitalhouse.clinicaOdontologica.entity.Turno;
import com.digitalhouse.clinicaOdontologica.service.ITurnoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    private ITurnoService turnoService;

    public TurnoController(ITurnoService turnoService) {
        this.turnoService = turnoService;
    }

    @PostMapping("/agregar")
    public ResponseEntity<?> gurdarTurno(@Valid @RequestBody TurnoRequestDTO turnoRequestDTO){
        TurnoResponseDTO turno1 = turnoService.saveTurno(turnoRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(turno1);


    }
    @GetMapping("/buscarTodos")
    public ResponseEntity<List<TurnoResponseDTO>> buscarTodos(){
        return ResponseEntity.ok(turnoService.getAll());
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarTurno(@PathVariable Integer id){
            turnoService.deleteTurno(id);
            return ResponseEntity.status(HttpStatus.OK).body("Turno fue eliminado");
    }

    @PutMapping("/modificar")
    public ResponseEntity<String> modificarTurno(@Valid @RequestBody TurnoUpdateDTO turno){
        turnoService.updateTurno(turno);
        return ResponseEntity.status(HttpStatus.OK).body("El turno fue actualizado");
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id){
        Optional<TurnoResponseDTO> turno = turnoService.getTurnoById(id);
        if(turno.isPresent()){
            return ResponseEntity.ok(turno.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El Turno no fue encontrado");
    }
}
