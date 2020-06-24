package br.com.ajocar.Ajocar.controllers;

import java.util.List;

import br.com.ajocar.Ajocar.dto.ClientDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
	@GetMapping("/view/{id}")
	public ModelAndView findOne(@PathVariable Integer id) {
		ModelAndView newClient = new ModelAndView();
		newClient.addObject("client",service.find(id));
		newClient.setViewName("clientView");
		return newClient;
	}
	@GetMapping("/new")
	public ModelAndView newClient() {
		ModelAndView newClient = new ModelAndView();
		newClient.setViewName("clientAdd");
		return newClient;
	}

	@PostMapping("/save")
	public String saveClient(@ModelAttribute("client") Client client) {
		Integer id = service.saveClient(client);
		return "redirect:/redirectPage/"+id;
	}

	@GetMapping(value = "/{id}")
	public ModelAndView find (@PathVariable Integer id){
		ModelAndView newClient = new ModelAndView();
		newClient.setViewName("clientAdd");
		newClient.addObject("client",service.find(id));
		return newClient;
	}



	@ModelAttribute(value = "client")
	public Client newClientObject(){return new Client();}

}
