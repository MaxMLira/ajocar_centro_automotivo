package br.com.ajocar.Ajocar.controllers;

import br.com.ajocar.Ajocar.services.CarService;
import br.com.ajocar.Ajocar.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ajax")
public class AjaxController {


    @Autowired
    private ClientService clientService;

    @Autowired
    private CarService carService;

    @PostMapping(value = "/searchClient",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> searchClient(@RequestBody  String term){
        if(clientService.searchClient(term) != null){
            return new ResponseEntity<>(clientService.searchClient(term),HttpStatus.OK);
        }
         return  new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/deleteCar",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteCar(@RequestBody  Integer id){
        try{
            if (id == null)
                return  new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
            else
                carService.deleteCar(id);
            return new ResponseEntity<>("OK",HttpStatus.OK);

        }catch (Exception e){
            return  new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }


    }
}
