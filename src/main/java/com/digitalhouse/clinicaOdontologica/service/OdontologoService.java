package com.digitalhouse.clinicaOdontologica.service;

import com.digitalhouse.clinicaOdontologica.dao.IDao;
import com.digitalhouse.clinicaOdontologica.model.Odontologo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoService {

    private IDao<Odontologo> odontologoIDao;

    public OdontologoService(IDao<Odontologo> veterinarioIDao) {
        this.odontologoIDao = veterinarioIDao;
    }

    public Odontologo saveOdontologo(Odontologo odontologo){
        return  odontologoIDao.save(odontologo);
    }

    public Odontologo getOdontologoById(int  id){
        return  odontologoIDao.getById(id);
    }
    public List<Odontologo> getAll(){
        return  odontologoIDao.getAll();
    }
    public void updateOdontologo(Odontologo odontologo){
        odontologoIDao.update(odontologo);
    }

    public void deleteOdontologo(Integer id){
        odontologoIDao.delete(id);
    }

}
