package com.digitalhouse.clinicaOdontologica.controller;

import com.digitalhouse.clinicaOdontologica.dto.request.TurnoRequestDTO;
import com.digitalhouse.clinicaOdontologica.dto.request.TurnoUpdateDTO;
import com.digitalhouse.clinicaOdontologica.dto.response.TurnoResponseDTO;
import com.digitalhouse.clinicaOdontologica.entity.Turno;
import com.digitalhouse.clinicaOdontologica.service.ITurnoService;
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
    public ResponseEntity<?> gurdarTurno(@RequestBody TurnoRequestDTO turnoRequestDTO){
        TurnoResponseDTO turno1 = turnoService.saveTurno(turnoRequestDTO);
        if (turno1!= null){
            return ResponseEntity.ok(turno1);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El paciente o el odontologo no fueron encontrados");
        }

    }
    @GetMapping("/buscarTodos")
    public ResponseEntity<List<TurnoResponseDTO>> buscarTodos(){
        return ResponseEntity.ok(turnoService.getAll());
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarTurno(@PathVariable Integer id){
        Optional<TurnoResponseDTO> turnoAEliminar = turnoService.getTurnoById(id);
        if(turnoAEliminar.isPresent()){
            turnoService.deleteTurno(id);
            return ResponseEntity.status(HttpStatus.OK).body("El turno fue eliminado");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El turno no fue encontrado para eliminarlo");
    }
    @PutMapping("/modificar")
    public ResponseEntity<String> modificarTurno(@RequestBody TurnoUpdateDTO turno){
        Optional<TurnoResponseDTO> turnoAModificar = turnoService.getTurnoById(turno.getId());
        if(turnoAModificar.isPresent()){
            turnoService.updateTurno(turno);
            return ResponseEntity.status(HttpStatus.OK).body("El turno fue actualizado");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El turno no fue encontrado para modificarlo");
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
