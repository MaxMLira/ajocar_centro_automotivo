package br.com.ajocar.Ajocar.services;

import br.com.ajocar.Ajocar.dto.ClientDto;
import br.com.ajocar.Ajocar.model.Car;
import br.com.ajocar.Ajocar.model.Client;
import br.com.ajocar.Ajocar.repositories.ClientRepository;
import br.com.ajocar.Ajocar.repositories.ClientRepositoryPage;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;
	@Autowired
	private ClientRepositoryPage pageRepository;

	@Autowired
	private CarService carService;

	public Page<Client> getAll(Pageable page) {



		return pageRepository.findAll(page);
	}

	public Integer saveClient(Client client) {
		repository.save(client);
		carService.saveCars(client.getCars(), client);

		return client.getId();
	}

	public void updateClient(Integer id, Client clientUpdate){
		Client client = find(id);
		List<Car> carsUpdated = client.update(clientUpdate);
		repository.save(client);
		carService.saveCars(carsUpdated,client);
	}


	public Client find(Integer id){
		Optional<Client> client = repository.findById(id);

		return client.orElseThrow(() -> new ObjectNotFoundException(
				"Cliente não encontrado", Client.class.getName()));
	}

	public Client searchClient(String term) {
		return repository.findClientByNameContainingOrTel(term,term);
	}
}
