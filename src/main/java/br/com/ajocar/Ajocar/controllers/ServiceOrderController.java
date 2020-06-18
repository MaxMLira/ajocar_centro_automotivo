package br.com.ajocar.Ajocar.controllers;

import br.com.ajocar.Ajocar.dto.ServiceOrderDto;
import br.com.ajocar.Ajocar.model.Client;
import br.com.ajocar.Ajocar.model.ServiceOrder;
import br.com.ajocar.Ajocar.services.ClientService;
import br.com.ajocar.Ajocar.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.ws.Response;
import java.util.List;

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
		List<ServiceOrder> serviceList = orderService.getAll();
		home.setViewName("serviceOrder");
		if (serviceList.isEmpty()) {
			home.addObject("noService", Boolean.TRUE);
		} else {
			home.addObject("noService", Boolean.FALSE);
			home.addObject("listOrder", serviceList);
		}
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
	public String save(@ModelAttribute("service") ServiceOrder service){
		try{
			orderService.saveOrderService(service);
			return "redirect:/serviceOrder";
		}catch (Exception e){
			return "redirect:/error/serviceOrderSave"; //todo cruar controler de erro
		}



	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> searchOrderServiceId(@PathVariable Integer id){
		ServiceOrder serviceOrder = orderService.SearchOrderServiceByID(id);

		return ResponseEntity.ok().body(serviceOrder);
	}

	@ModelAttribute(value = "service")
	public ServiceOrder newService(){
		return new ServiceOrder();
	}
}
