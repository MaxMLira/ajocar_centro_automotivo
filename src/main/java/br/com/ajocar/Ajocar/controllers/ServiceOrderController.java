package br.com.ajocar.Ajocar.controllers;

import br.com.ajocar.Ajocar.dto.ServiceOrderDto;
import br.com.ajocar.Ajocar.model.Client;
import br.com.ajocar.Ajocar.model.ServiceOrder;
import br.com.ajocar.Ajocar.services.ClientService;
import br.com.ajocar.Ajocar.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.ws.Response;

@Controller
@RequestMapping("/serviceOrder")
public class ServiceOrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private ClientService clientService;

	@GetMapping("")
	public ModelAndView index() {

		ModelAndView home = new ModelAndView();

		home.setViewName("serviceOrder");
		return home;
	}

	@GetMapping("/new/{idClient}")
	public ModelAndView newserviceOrder(@PathVariable  Integer idClient) {
		ModelAndView newServiceOrder = new ModelAndView();
		Client client = clientService.find(idClient);
		newServiceOrder.addObject("client",idClient);
		newServiceOrder.addObject("nome",client.getName());
		newServiceOrder.addObject("cars",client.getCars());
		newServiceOrder.setViewName("serviceAdd");
		return newServiceOrder;
	}

	@PostMapping("/save")
	public String save(ServiceOrderDto order){

		return "ok ".concat(order.toString());

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> searchOrderServiceId(@PathVariable Integer id){
		ServiceOrder serviceOrder = orderService.SearchOrderServiceByID(id);

		return ResponseEntity.ok().body(serviceOrder);
	}

}
