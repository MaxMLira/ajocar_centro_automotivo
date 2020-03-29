package br.com.ajocar.Ajocar.controllers;

import br.com.ajocar.Ajocar.model.ServiceOrder;
import br.com.ajocar.Ajocar.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.ws.Response;

@Controller
@RequestMapping("/serviceOrder")
public class ServiceOrderController {

	@Autowired
	private OrderService orderService;

	@GetMapping("")
	public ModelAndView index() {

		ModelAndView home = new ModelAndView();

		home.setViewName("serviceOrder");
		return home;
	}

	@GetMapping("/new")
	public ModelAndView newserviceOrder() {
		ModelAndView newServiceOrder = new ModelAndView();
		newServiceOrder.setViewName("serviceAdd");
		return newServiceOrder;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> searchOrderServiceId(@PathVariable Integer id){
		ServiceOrder serviceOrder = orderService.SearchOrderServiceByID(id);

		return ResponseEntity.ok().body(serviceOrder);
	}

}
