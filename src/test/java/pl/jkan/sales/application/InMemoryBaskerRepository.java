package pl.jkan.sales.application;

import pl.jkan.sales.Basket;
import pl.jkan.sales.BasketRepository;
import pl.jkan.sales.ClientId;

import java.util.HashMap;

public class InMemoryBaskerRepository implements BasketRepository {

    private HashMap<String, Basket> baskets;

    public InMemoryBaskerRepository() {
        this.baskets = new HashMap<>();
    }
    @Override
    public Basket getBasketForClient(ClientId clientId) {
        if (baskets.containsKey(clientId.getId())) {
            return baskets.get(clientId.getId());
        }

        Basket basket = new Basket();

        baskets.put(clientId.getId(), basket);

        return basket;
    }
}
