package br.com.ajocar.Ajocar.services;

import br.com.ajocar.Ajocar.model.Product;
import br.com.ajocar.Ajocar.model.ServiceOrder;
import br.com.ajocar.Ajocar.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {


    @Autowired
    ProductRepository repository;

    public void saveProducts(List<Product> products, ServiceOrder serviceOrder) {
        products.forEach(product -> product.setServiceOrder(serviceOrder));
        repository.saveAll(products);
    }

    public void deleteProduct(Integer id) {
        repository.deleteById(id);
    }
}
