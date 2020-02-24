package br.com.ajocar.Ajocar.services;


import br.com.ajocar.Ajocar.model.Client;
import br.com.ajocar.Ajocar.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;


    public List<Client> getAll(){
        return repository.findAll();
    }


}
