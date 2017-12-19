package pl.jkan.sales.application;

import pl.jkan.sales.Basket;
import pl.jkan.sales.BasketRepository;
import pl.jkan.sales.Product;

public class AddToBasketUseCase {

    private BasketRepository basketRepository;
    private UserContext userContext;
    private ProductCatalog productCatalog;

    public AddToBasketUseCase(BasketRepository basketRepository, UserContext userContext, ProductCatalog productCatalog) {
        this.basketRepository = basketRepository;
        this.userContext = userContext;
        this.productCatalog = productCatalog;
    }

    public void addProduct(String productName) {
        Product product = productCatalog.getProduct(productName);
        Basket basket = basketRepository.getBasketForClient(userContext.getCurrentUserId());

        basket.add(product);
    }
}
