package br.com.ajocar.Ajocar.controllers;

import br.com.ajocar.Ajocar.model.Client;
import br.com.ajocar.Ajocar.services.ClientService;
import br.com.ajocar.Ajocar.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/client")
public class ClientController {

	@Autowired
	private ClientService service;
	@Autowired
	private OrderService serviceOrder;

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
		Client client = service.find(id);
		newClient.addObject("client", client);
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
	@PostMapping("/update/{id}")
	public String updateUser(@PathVariable("id") Integer id, @Valid Client client ) {
		service.updateClient(id,client);
		return "redirect:/client";
	}

	@GetMapping(value = "/{id}")
	public ModelAndView find (@PathVariable Integer id){
		ModelAndView newClient = new ModelAndView();
		newClient.setViewName("clientUpd");
		Client client = service.find(id);
		newClient.addObject("cars",client.getCars());
		newClient.addObject("client", client);
		return newClient;
	}



	@ModelAttribute(value = "client")
	public Client newClientObject(){return new Client();}

}
