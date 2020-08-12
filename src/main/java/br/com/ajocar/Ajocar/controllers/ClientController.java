package br.com.ajocar.Ajocar.controllers;

import java.util.List;

import br.com.ajocar.Ajocar.dto.ClientDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import br.com.ajocar.Ajocar.model.Client;
import br.com.ajocar.Ajocar.services.ClientService;

import javax.validation.Valid;

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
		Client client = service.find(id);
		newClient.addObject("client", client);
		newClient.addObject("cars",client.getCars());
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
		//TODO faer o metodo para atualizar os carros manin
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
