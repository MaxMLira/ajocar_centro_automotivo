package br.com.ajocar.Ajocar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ajocar.Ajocar.model.ServiceOrder;

public interface ServiceOrderRepository extends JpaRepository<ServiceOrder, Integer>{
	
}
