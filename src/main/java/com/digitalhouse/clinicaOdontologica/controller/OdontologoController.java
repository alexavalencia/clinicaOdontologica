package com.digitalhouse.clinicaOdontologica.controller;

import com.digitalhouse.clinicaOdontologica.model.Odontologo;
import com.digitalhouse.clinicaOdontologica.service.OdontologoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class OdontologoController {

    private OdontologoService odontologoService;

    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @GetMapping("/odontologo/{id}")
    public String mostrarOdontologoPorId(Model model, @PathVariable Integer id){
        Odontologo odontologo = odontologoService.getOdontologoById(id);
        model.addAttribute("nombre",odontologo.getNombre());
        model.addAttribute("apellido",odontologo.getApellido());
        return "odontologo";

    }

}
