package pl.jkan.sales.application;

import pl.jkan.sales.Product;

import java.util.HashMap;

public class InMemoryProductCatalog {
    private HashMap<String, Product> products;

    public InMemoryProductCatalog() {
        this.products = new HashMap<>();
    }

    public void add(Product p) {
        products.put(p.getName(), p);
    }

    public Product getProduct(String name) {
        return products.get(name);
    }
}
