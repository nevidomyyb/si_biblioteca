package com.sis_biblioteca.sis_biblioteca;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SisBibliotecaApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SisBibliotecaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Digite um texto:");
		String texto = scanner.nextLine();
		System.out.println("Você digitou: " + texto);
		scanner.close();
	}
}
