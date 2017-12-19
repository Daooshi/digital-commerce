package pl.jkan.sales;

public interface BasketRepository {
    public Basket getBasketForClient(ClientId clientId);
}
