package pl.jkan.przelewy24;

import org.junit.Assert;
import org.junit.Test;
import pl.jkan.support.http.Request;
import pl.jkan.support.http.Response;
import pl.jkan.support.http.SpyHttp;

import java.util.HashMap;
import java.util.Map;

public class ApiClientTest {


    @Test
    public void itAllowRegisterTransaction() {
        RegisterTransactionData data = new RegisterTransactionData();
        SpyHttp http = new SpyHttp(new Response(200, new HashMap<>(), ""));

        Przelewy24Api api = new Przelewy24Api(http, "123456", "crc_key");

        api.registerTransaction(data);
        Map<String, String> pagams = http.lastRequest.getParams();

        Assert.assertTrue(pagams.containsKey("p24_merchant_id"));
        Assert.assertTrue(pagams.containsKey("p24_pos_id"));
        Assert.assertTrue(pagams.containsKey("p24_session_id"));
        Assert.assertTrue(pagams.containsKey("p24_amount"));
        Assert.assertTrue(pagams.containsKey("p24_currency"));
        Assert.assertTrue(pagams.containsKey("p24_description"));
        Assert.assertTrue(pagams.containsKey("p24_email"));
        Assert.assertTrue(pagams.containsKey("p24_country"));
        Assert.assertTrue(pagams.containsKey("p24_url_return"));
        Assert.assertTrue(pagams.containsKey("p24_api_version"));
        Assert.assertTrue(pagams.containsKey("p24_sign"));
    }

    @Test
    public void itAllowRegisterTransactionWithAppropriateSign() {
        RegisterTransactionData data = new RegisterTransactionData(
            "1234qwe",
                555,
                "PLN"
        );
        SpyHttp http = new SpyHttp(new Response(200, new HashMap<>(), ""));

        Przelewy24Api api = new Przelewy24Api(http, "123456", "crc_key");

        api.registerTransaction(data);
        Map<String, String> pagams = http.lastRequest.getParams();

        Assert.assertEquals(
            "FC89FE50099E742F9E3FA9B87FFBEF1D",
            pagams.get("p24_sign")
        );
    }
}
