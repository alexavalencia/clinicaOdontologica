package com.digitalhouse.clinicaOdontologica.dao.imp;

import com.digitalhouse.clinicaOdontologica.dao.IDao;
import com.digitalhouse.clinicaOdontologica.model.Turno;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Repository
public class TurnoMemoria implements IDao<Turno> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TurnoMemoria.class);
    private List<Turno> turnos = new ArrayList<>();

    @Override
    public Turno save(Turno turno) {
        turno.setId(turnos.size()+1);
        turnos.add(turno);
        LOGGER.info("Turno agregado " + turno);
        return turno;
    }

    @Override
    public Turno getById(Integer id) {
        Turno turnoRetorno = turnos.stream().filter(turno -> turno.getId()== id).findFirst().or(null).get();
        if(turnoRetorno != null){
            LOGGER.info("Turno encontrado "+ turnoRetorno);
            return turnoRetorno;
        }
        return null;
    }

    @Override
    public List<Turno> getAll() {
        if(turnos.size()>0){
            LOGGER.info("Turnos encontrados "+ turnos);
            return turnos;
        }
        return null;
    }

    @Override
    public void update(Turno turno) {
        Turno turnoActualizar = turnos.stream().filter(x -> x.getId()== turno.getId()).findFirst().get();
        turnoActualizar.setPaciente(turno.getPaciente());
        turnoActualizar.setOdontologo(turno.getOdontologo());
        turnoActualizar.setFecha(turno.getFecha());
        LOGGER.info("El turno fue actualizado correctamente"+ turno);
    }

    @Override
    public void delete(Integer id) {
        turnos.remove(id-1);
        LOGGER.info("El turno fue eliminado");
    }
}
