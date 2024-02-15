package com.alexdev.app.springbootdatajpa;

import com.alexdev.app.springbootdatajpa.services.IUploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootDataJpaApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDataJpaApplication.class, args);
	}

	@Autowired
	IUploadFileService fileService;
	@Override
	public void run(String... args) throws Exception {
		/*
		Cada vez que inicie el servidor ocurrira dos cosas:

			* Se eleminaran todos los archivos que se encuentran
			en el directorio de las imagenes
			* Se creara el directorio 'uploads' donde justamente se almacenaran las imagenes
		*/

		fileService.deleteAll();
		fileService.init();
	}
}