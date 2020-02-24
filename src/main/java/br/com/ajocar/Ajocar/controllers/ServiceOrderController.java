package br.com.ajocar.Ajocar.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/serviceOrder")
public class ServiceOrderController {
	@GetMapping("")
	public ModelAndView index() {

		ModelAndView home = new ModelAndView();

		home.setViewName("serviceOrder");
		return home;
	}
}
