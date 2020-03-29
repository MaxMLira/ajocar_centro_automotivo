package br.com.ajocar.Ajocar.services;

import br.com.ajocar.Ajocar.dto.ClientDto;
import br.com.ajocar.Ajocar.model.Client;
import br.com.ajocar.Ajocar.repositories.ClientRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;

	@Autowired
	private CarService carService;

	public List<Client> getAll() {
		return repository.findAll();
	}

	public String saveClient(ClientDto clientDto) {
		Client client = Client.toModel(clientDto);

		String message = null;

		if (client.getName() == null || client.getName().isEmpty()) {
			message = "O nome do cliente não pode estar vazio!";
			return message;
		}
		if (client.getTel() == null || client.getTel().isEmpty()) {
			message = "O telefone deve ser cadastrado!";
			return message;
		}
		message = verificyAddress(client);
		if(!message.equals("")) {
			return message;
		}

		if (client.getCars() == null || client.getCars().size() == 0) {
			message = "Pelo menos um carro deve ser cadastrado!";
			return message;
		}
		repository.save(client);
		carService.saveCars(client.getCars(), client);

		return "Cadastrado com sucesso!";
	}

	public ClientDto getOne(){
		return null;
	}

	public void updateClient(){

	}

	private String verificyAddress(Client client) {

		if (client.getAddress() == null) {
			return "O Endereço deve ser cadastrado!";
		}
		if (client.getAddress().getAddress() == null || client.getAddress().getAddress().isEmpty()) {
			return "A rua é obrigatória!";
		}
		if (client.getAddress().getNumber() == null ||client.getAddress().getNumber().isEmpty()) {
			return "O número da residência é obrigatório!";
		}
		if (client.getAddress().getDistric() == null || client.getAddress().getDistric().isEmpty()) {
			return "O nome do bairro é obrigatório!";
		}
		if (client.getAddress().getState()  == null || client.getAddress().getState().isEmpty()) {
			return "O nome do estado é obrigatório!";
		}

		return "";

	}

	public Client find(Integer id){
		Optional<Client> client = repository.findById(id);

		return client.orElseThrow(() -> new ObjectNotFoundException(
				"Cliente não encontrado", Client.class.getName()));
	}
}
