package br.com.ajocar.Ajocar;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.ajocar.Ajocar.model.Address;
import br.com.ajocar.Ajocar.model.Car;
import br.com.ajocar.Ajocar.model.Client;
import br.com.ajocar.Ajocar.model.Product;
import br.com.ajocar.Ajocar.model.ServiceOrder;
import br.com.ajocar.Ajocar.repositories.CarRepository;
import br.com.ajocar.Ajocar.repositories.ClientRepository;
import br.com.ajocar.Ajocar.repositories.ProductRepository;
import br.com.ajocar.Ajocar.repositories.ServiceOrderRepository;

@SpringBootApplication
public class AjocarApplication implements CommandLineRunner{

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

	@Override
	public void run(String... args) throws Exception {
		Address a = new Address(null,"rua francisco","123","lourdes","sp");
		
		Client c = new Client(null,"Lucas","123456",a);
		Car car01 = new Car(null,"Palio","253-12sdX","preto","diesel",3360);
		Car car02 = new Car(null,"Gol","1350-1dX","branco","gasolina",360);
		ServiceOrder s1 = new ServiceOrder(null, "Fi service chato", 500.00);
		ServiceOrder s2 = new ServiceOrder(null, "Fi service legal", 400.00);
		Product p1 = new Product(null, "Cambio", 1, 250.00);
		Product p2 = new Product(null, "volante", 4, 100.00);
		Product p3 = new Product(null, "Embreagem", 3, 80.00);
		Product p4 = new Product(null, "Acelerador", 2, 50.00);
		c.setServiceOrders(Arrays.asList(s1,s2));
		s1.setClient(c);
		s2.setClient(c);
		c.setCars(Arrays.asList(car01,car02));
		s1.setProducts(Arrays.asList(p1,p2));
		s2.setProducts(Arrays.asList(p3,p4));
		clientRepo.save(c);
		car01.setClient(c);
		car02.setClient(c);
		p1.setServiceOrder(s1);
		p2.setServiceOrder(s1);
		p3.setServiceOrder(s2);
		p4.setServiceOrder(s2);

		serviceRepo.saveAll(Arrays.asList(s1,s2));
		productRepo.saveAll(Arrays.asList(p1,p2,p3,p4));
		carRepo.saveAll(Arrays.asList(car01,car02));


		
		
		
		
		
		
	}

	

}
