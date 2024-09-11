package com.digitalhouse.clinicaOdontologica.controller;

import com.digitalhouse.clinicaOdontologica.dto.response.EspecialidadResponseDTO;
import com.digitalhouse.clinicaOdontologica.entity.Especialidad;
import com.digitalhouse.clinicaOdontologica.entity.Paciente;
import com.digitalhouse.clinicaOdontologica.service.IEspecialidadService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/especialidad")
public class EspecialidadController {

    private IEspecialidadService especialidadService;

    public EspecialidadController(IEspecialidadService especialidadService) {
        this.especialidadService = especialidadService;
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Integer id){
        Optional<EspecialidadResponseDTO> especialidad = especialidadService.getEspecialidadById(id);
        if(especialidad.isPresent()){
            return ResponseEntity.ok(especialidad.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro la especialidad") ;

    }

    @GetMapping("/buscarTodos")
    public ResponseEntity<?> buscarTodos(){
        List<EspecialidadResponseDTO> especialidadList = especialidadService.getAll();
        if(!especialidadList.isEmpty()){
            return ResponseEntity.ok(especialidadList);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay especialidades ingresadas") ;


    }

    @PostMapping("/agregar")
    public ResponseEntity<EspecialidadResponseDTO> agregarEspecialidad(@Valid @RequestBody Especialidad especialidad){
        return ResponseEntity.ok(especialidadService.saveEspecialidad(especialidad));
    }
}
