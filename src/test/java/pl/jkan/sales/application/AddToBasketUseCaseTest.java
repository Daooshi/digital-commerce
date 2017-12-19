package pl.jkan.sales.application;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.jkan.sales.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AddToBasketUseCaseTest {

    private BasketRepository basketRepository;

    @Before
    public void setup() {
        this.basketRepository = new InMemoryBaskerRepository();
    }

    @Test
    public void addProductToBasketForSpecificCustomer() {
        //mam
        clientExists(new ClientId("kuba_kanclerz"));
        productExists("lego 8293");

        //akcja
        AddToBasketUseCase useCase = new AddToBasketUseCase(
                basketRepository,
                systemUserContext,
                productCatalog
        );
        useCase.addProduct("lego 8293");

        //czego oczekujemy
        thereIsNotEmptyBasketForClient(new ClientId("kuba_kanclerz"));
        thereIsProductInCustomerBasket(new ClientId("kuba_kanclerz"), "lego 8293");
    }

    private void thereIsProductInCustomerBasket(ClientId clientId, String productName) {
        Basket basket = basketRepository.getBasketForClient(clientId);

        List<BasketItem> matched = basket.getReservedProducts()
            .stream()
            .filter(basketItem -> basketItem.getName().equals(productName))
            .collect(Collectors.toList())
        ;

        Assert.assertFalse(matched.isEmpty());
    }

    private void thereIsNotEmptyBasketForClient(ClientId clientId) {
        Basket basket = basketRepository.getBasketForClient(clientId);

        Assert.assertFalse(basket.isEmpty());
    }

    private void productExists(String name) {
        productCatalog.add(new Product(name, 10));
    }

    private void clientExists(ClientId clientId) {
        systemUserContext.authenticate(clientId);
    }
}
