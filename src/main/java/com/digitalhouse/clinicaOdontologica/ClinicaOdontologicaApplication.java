package com.digitalhouse.clinicaOdontologica;

import com.digitalhouse.clinicaOdontologica.db.H2Connection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClinicaOdontologicaApplication {

	public static void main(String[] args) {
		H2Connection.crearTablas();
		SpringApplication.run(ClinicaOdontologicaApplication.class, args);
	}

}
