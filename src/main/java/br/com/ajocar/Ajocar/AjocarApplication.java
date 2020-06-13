package br.com.ajocar.Ajocar;

import br.com.ajocar.Ajocar.repositories.CarRepository;
import br.com.ajocar.Ajocar.repositories.ClientRepository;
import br.com.ajocar.Ajocar.repositories.ProductRepository;
import br.com.ajocar.Ajocar.repositories.ServiceOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AjocarApplication{

	@Autowired
	ClientRepository clientRepo;
	@Autowired
	CarRepository carRepo;
	@Autowired
	ServiceOrderRepository serviceRepo;
	@Autowired
	ProductRepository productRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(AjocarApplication.class, args);
	}



}
