package br.com.ajocar.Ajocar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ajocar.Ajocar.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{
	
}
