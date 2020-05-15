package br.com.ajocar.Ajocar.services;

import br.com.ajocar.Ajocar.model.ServiceOrder;
import br.com.ajocar.Ajocar.repositories.ServiceOrderRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private ServiceOrderRepository repository;

    @Autowired
    ProductService productService;

    public Boolean saveOrderService(ServiceOrder order){
        order.setClient(order.getCar().getClient());
        repository.save(order);
        productService.saveProducts(order.getProducts(),order);
        return Boolean.TRUE;
    }

    public ServiceOrder SearchOrderServiceByID(Integer id){
        Optional<ServiceOrder> orderService = repository.findById(id);

        return orderService.orElseThrow(() -> new ObjectNotFoundException(
                "Ordem de Serviço não encontrado", ServiceOrder.class.getName()));
    }

}
