package it.geek.inv.service;

import it.geek.inv.pojo.Product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class SimpleProductManager implements ProductManager {

    private List<Product> products;
    
    public List<Product> getProducts() {
    	if(products==null){
    		products = new ArrayList<>();
    	}
    	if(products.isEmpty()){
    		Product p1 = new Product("Lamp", 5.75);
    		products.add(p1);
    		Product p2 = new Product("Table", 75.25);
    		products.add(p2);
    		Product p3 = new Product("Chair", 22.79);
    		products.add(p3);
    	}
        return products;
    }

    public void increasePrice(int percentage) {
        if (products != null) {
            for (Product product : products) {
                double newPrice = product.getPrice().doubleValue() * 
                                    (100 + percentage)/100;
                product.setPrice(newPrice);
            }
        }   
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
    
}
