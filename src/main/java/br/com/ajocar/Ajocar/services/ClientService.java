package br.com.ajocar.Ajocar.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ajocar.Ajocar.model.Client;
import br.com.ajocar.Ajocar.repositories.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;

	@Autowired
	private CarService carService;
	
	public List<Client> getAll() {
		return repository.findAll();
	}

	public String saveClient(Client client) {
		if (client.getName().isEmpty()) {
			return "O nome do cliente não pode estar vazio!";
		}
		if (client.getTel().isEmpty()) {
			return "O telefone deve ser cadastrado!";
		}
		if (client.getAddress() == null) {
			return "O Endereço deve ser cadastrado!";
		}
		if (client.getCars() == null || client.getCars().size() == 0) {
			return "Pelo menos um carro deve ser cadastrado!";
		}
		repository.save(client);
		carService.saveCars(client.getCars()); 

		return "Cadastrado com sucesso!";
	}

}
