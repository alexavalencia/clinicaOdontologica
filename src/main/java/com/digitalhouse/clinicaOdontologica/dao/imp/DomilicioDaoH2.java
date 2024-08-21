package com.digitalhouse.clinicaOdontologica.dao.imp;

import com.digitalhouse.clinicaOdontologica.dao.IDao;
import com.digitalhouse.clinicaOdontologica.db.H2Connection;
import com.digitalhouse.clinicaOdontologica.model.Domicilio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DomilicioDaoH2 implements IDao<Domicilio> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DomilicioDaoH2.class);
    private static final String INSERT="INSERT INTO DOMICILIOS VALUES( DEFAULT, ?,?,?,?)";

    private  static final String SELECT_BY_ID="SELECT * FROM DOMICILIOS WHERE ID=? ";
    private  static final String SELECT_ALL="SELECT * FROM DOMICILIOS";

    private static final String UPDATE= "UPDATE DOMICILIOS SET CALLE=?, NUMERO=?, LOCALIDAD=?, PROVINCIA=? WHERE ID=?";
    private static final String DELETE= "DELETE FROM DOMICILIOS WHERE ID=?";
    private Connection connection= null;
    @Override
    public Domicilio save(Domicilio domicilio) {
        Domicilio domicilioARetornar = null;
        try {
            connection= H2Connection.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,domicilio.getCalle());
            preparedStatement.setInt(2,domicilio.getNumero());
            preparedStatement.setString(3,domicilio.getLocalidad());
            preparedStatement.setString(4,domicilio.getProvincia());
            preparedStatement.executeUpdate();
            connection.commit();
            ResultSet resultSet= preparedStatement.getGeneratedKeys();
            Integer id = 0;
            if(resultSet.next()){
                id= resultSet.getInt(1);
                domicilioARetornar= new Domicilio(id,domicilio.getCalle(),domicilio.getNumero(),domicilio.getLocalidad(),domicilio.getLocalidad());
            }
            LOGGER.info("Domicilio guardado "+domicilioARetornar);

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
        return domicilioARetornar;
    }

    @Override
    public Domicilio getById(Integer id) {
        Domicilio domicilioResult = null;
        try{
            connection = H2Connection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID);
            preparedStatement.setInt(1,id);
            ResultSet resultSet= preparedStatement.executeQuery();
            while (resultSet.next()){
                domicilioResult= new Domicilio(resultSet.getInt(1),resultSet.getString(2),resultSet.getInt(3),resultSet.getString(4),resultSet.getString(5));
                LOGGER.info("Domicilio obtenido "+domicilioResult);
            }
            if (domicilioResult== null){
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
        return domicilioResult;
    }

    @Override
    public List<Domicilio> getAll() {
        Domicilio domicilio= null;
        List<Domicilio> domicilios= new ArrayList<>();
        try {
            connection = H2Connection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet=statement.executeQuery(SELECT_ALL);
            while (resultSet.next()){
                domicilio= new Domicilio(resultSet.getInt(1),resultSet.getString(2),resultSet.getInt(3),resultSet.getString(4),resultSet.getString(5));
                domicilios.add(domicilio);
            }
            if(!domicilios.isEmpty()){
                LOGGER.info("Domicilios encontrados "+ domicilios);
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
        return domicilios;
    }

    @Override
    public void update(Domicilio domicilio) {
        try {
            connection= H2Connection.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1,domicilio.getCalle());
            preparedStatement.setInt(2,domicilio.getNumero());
            preparedStatement.setString(3,domicilio.getLocalidad());
            preparedStatement.setString(4,domicilio.getProvincia());
            preparedStatement.setInt(5,domicilio.getId());
            preparedStatement.executeUpdate();
            connection.commit();
            LOGGER.info("Domicilio actualizado "+domicilio);

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


    }

    @Override
    public void delete(Integer id) {
        try {
            connection= H2Connection.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
            connection.commit();
            LOGGER.info("Domicilio Eliminado ");

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
    }
}
