package pl.jkan.przelewy24;

public class RegisterTransactionData {
    private String sessionId;
    private Integer amount;
    private String currency;

    public RegisterTransactionData() {

    }

    public RegisterTransactionData(String sessionId, Integer amount, String currency) {
        this.sessionId = sessionId;
        this.amount = amount;
        this.currency = currency;
    }

    public String getSessionId() {
        return sessionId;
    }

    public Integer getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }
}
