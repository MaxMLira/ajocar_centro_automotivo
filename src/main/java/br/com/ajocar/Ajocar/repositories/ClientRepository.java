package br.com.ajocar.Ajocar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ajocar.Ajocar.model.Client;

public interface ClientRepository extends JpaRepository<Client, Integer>{

    Client findClientByNameContainingOrTel(String term,String ter);
}
