package pl.jkan.sales.application;

import pl.jkan.sales.ClientId;

public class InMemoryUserContext {
    private ClientId currentClient;

    public ClientId getCurrentUserId() {
        return currentClient;
    }

    public void authenticate(ClientId id) {
        currentClient = id;
    }
}
