package br.com.ajocar.Ajocar.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.ajocar.Ajocar.model.Client;
import br.com.ajocar.Ajocar.services.ClientService;

@Controller
@RequestMapping("/client")
public class ClientController {

	@Autowired
	private ClientService service;

	@GetMapping("")
	public ModelAndView index() {
		ModelAndView home = new ModelAndView();
		List<Client> clients = service.getAll();
		home.setViewName("client");
		home.addObject("clients", clients);
		return home;
	}

	@GetMapping("/new")
	public ModelAndView newClient() {
		ModelAndView newClient = new ModelAndView();
		newClient.setViewName("clientAdd");
		return newClient;
	}

	@PostMapping("/save")
	public String saveClient(@RequestBody Client client) {
		return service.saveClient(client);
	}
	

}
