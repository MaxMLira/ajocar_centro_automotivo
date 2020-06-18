package br.com.ajocar.Ajocar.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/redirectPage")
public class RedirectController {

    @GetMapping("/{idClient}")
    public ModelAndView redirectToOrderPage(@PathVariable Integer idClient){

        ModelAndView redirect = new ModelAndView();

        redirect.setViewName("redirect");
        redirect.addObject("client",idClient);
        return redirect;

    }

    @GetMapping("/newService")
    public ModelAndView redirectToNewService(){

        ModelAndView redirect = new ModelAndView();

        redirect.setViewName("redirectNewOrder");

        return redirect;

    }

}
