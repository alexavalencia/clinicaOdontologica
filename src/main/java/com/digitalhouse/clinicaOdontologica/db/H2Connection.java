package com.digitalhouse.clinicaOdontologica.db;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2Connection {

    private static final Logger LOGGER = LoggerFactory.getLogger(H2Connection.class);
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:./clinicaOdontologica","sa","sa");
    }

    public static void crearTablas(){
        Connection connection=null;
        try{
            Class.forName("org.h2.Driver");
            connection= DriverManager.getConnection("jdbc:h2:./clinicaOdontologica;INIT=RUNSCRIPT FROM 'create.sql'","sa","sa");

        }catch (Exception e){
            LOGGER.error(e.getMessage());
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }
}
