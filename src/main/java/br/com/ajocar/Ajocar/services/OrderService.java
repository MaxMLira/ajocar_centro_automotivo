package br.com.ajocar.Ajocar.services;

import br.com.ajocar.Ajocar.model.Product;
import br.com.ajocar.Ajocar.model.ServiceOrder;
import br.com.ajocar.Ajocar.repositories.ServiceOrderRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private ServiceOrderRepository repository;

    @Autowired
    ProductService productService;

    public Boolean saveOrderService(ServiceOrder order){
        order.setClient(order.getCar().getClient());
        order.setCreatIn(LocalDate.now());
        repository.save(order);
        productService.saveProducts(order.getProducts(),order);
        return Boolean.TRUE;
    }

    public ServiceOrder searchOrderServiceByID(Integer id){
        Optional<ServiceOrder> orderService = repository.findById(id);

        return orderService.orElseThrow(() -> new ObjectNotFoundException(
                "Ordem de Serviço não encontrado", ServiceOrder.class.getName()));
    }

    public List<ServiceOrder> getAll() {

        return repository.findAll();
    }

    public void updateOrderService(ServiceOrder serviceUpd) {
        ServiceOrder serviceOrder = repository.getOne(serviceUpd.getId());
        List<Product> products = serviceOrder.update(serviceUpd);
        repository.save(serviceOrder);
        productService.saveProducts(products,serviceOrder);

    }
}
