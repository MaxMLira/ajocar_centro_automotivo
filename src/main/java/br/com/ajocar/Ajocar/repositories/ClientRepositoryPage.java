package br.com.ajocar.Ajocar.repositories;

import br.com.ajocar.Ajocar.model.Client;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ClientRepositoryPage extends PagingAndSortingRepository<Client, Long> {
}
