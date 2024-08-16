package com.digitalhouse.clinicaOdontologica.dao.imp;

import com.digitalhouse.clinicaOdontologica.dao.IDao;
import com.digitalhouse.clinicaOdontologica.db.H2Connection;
import com.digitalhouse.clinicaOdontologica.model.Domicilio;
import com.digitalhouse.clinicaOdontologica.model.Paciente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PacienteDaoH2 implements IDao<Paciente> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PacienteDaoH2.class);
    private static final String INSERT="INSERT INTO PACIENTES VALUES( DEFAULT, ?,?,?,?,?)";

    private static final  String SELECT_BY_ID="SELECT * FROM PACIENTES WHERE ID=?";

    private  static final String SELECT_ALL="SELECT * FROM PACIENTES";
    private Connection connection= null;
    @Override
    public Paciente save(Paciente paciente) {
        Paciente pacienteRetornar = null;
        DomilicioDaoH2 domilicioDaoH2 = new DomilicioDaoH2();
        Domicilio domicilio = domilicioDaoH2.save(paciente.getDomicilio());

        try {
            connection= H2Connection.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,paciente.getApellido());
            preparedStatement.setString(2,paciente.getNombre());
            preparedStatement.setString(3,paciente.getDni());
            preparedStatement.setDate(4, Date.valueOf(paciente.getFechaIngreso()));
            preparedStatement.setInt(5,domicilio.getId());
            preparedStatement.executeUpdate();
            connection.commit();
            ResultSet resultSet= preparedStatement.getGeneratedKeys();
            Integer id = 0;
            if(resultSet.next()){
                id= resultSet.getInt(1);
                pacienteRetornar= new Paciente(id,paciente.getApellido(),paciente.getNombre(),paciente.getDni(),paciente.getFechaIngreso(),domicilio);
            }
            LOGGER.info("Paciente guardado "+pacienteRetornar);

        }catch (Exception e){
            LOGGER.error(e.getMessage());
            try {
                connection.rollback();
            } catch (SQLException ex) {
                LOGGER.error(ex.getMessage());
            }finally {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException ex) {
                    LOGGER.error(ex.getMessage());
                }
            }

        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }
        return pacienteRetornar;
    }

    @Override
    public Paciente getById(Integer id) {
        Domicilio domicilioResult = null;
        Paciente pacienteResult =null;
        try{
            connection = H2Connection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);
            preparedStatement.setInt(1,id);
            ResultSet resultSet= preparedStatement.executeQuery();
            while (resultSet.next()){
                domicilioResult = new DomilicioDaoH2().getById(resultSet.getInt(6));
                pacienteResult= new Paciente(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4), resultSet.getDate(5).toLocalDate(),domicilioResult);
                LOGGER.info("Paciente obtenido "+ pacienteResult);
            }
            if (pacienteResult==null){
                LOGGER.info("No se encontro el domicilio");
            }


        }catch (Exception e){
            LOGGER.error(e.getMessage());
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }
        return pacienteResult;
    }

    @Override
    public List<Paciente> getAll() {
        Paciente paciente= null;
        List<Paciente> pacientes= new ArrayList<>();
        List<Domicilio> domicilios= new DomilicioDaoH2().getAll();
        int cont=0;
        try {
            connection = H2Connection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet=statement.executeQuery(SELECT_ALL);
            while (resultSet.next()){
                paciente= new Paciente(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4), resultSet.getDate(5).toLocalDate(),domicilios.get(cont));
                pacientes.add(paciente);
                cont++;
            }
            if(!pacientes.isEmpty()){
                LOGGER.info("Domicilios encontrados "+ pacientes);
            }else{
                LOGGER.info("domicilios no se encontraron");
            }


        } catch (Exception e) {
            LOGGER.error(e.getMessage());

        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                LOGGER.error(ex.getMessage());
            }
        }
        return pacientes;
    }
}
