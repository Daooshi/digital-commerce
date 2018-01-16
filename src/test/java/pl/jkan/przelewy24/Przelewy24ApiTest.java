package pl.jkan.przelewy24;

import org.junit.Assert;
import org.junit.Test;
import pl.jkan.support.http.Request;
import pl.jkan.support.http.Response;
import pl.jkan.support.http.SpyHttp;

import java.util.HashMap;
import java.util.Map;

public class Przelewy24ApiTest {
    @Test
    public void itAllowForPerformTestConnection() {
        SpyHttp http = new SpyHttp(new Response(200, new HashMap<>(), ""));
        MojByt api = new MojByt(http, "123456", "crc_1234567");

        api.testConnection();

        Request lastRequest = http.lastRequest;

        Map<String, String> params = lastRequest.getParams();
        Assert.assertTrue(params.containsKey("p24_merchant_id"));
        Assert.assertTrue(params.containsKey("p24_pos_id"));
        Assert.assertTrue(params.containsKey("p24_sign"));

        Assert.assertEquals("123456", params.get("p24_merchant_id"));
        Assert.assertEquals("123456", params.get("p24_pos_id"));
    }

    @Test
    public void itAllowForPerformSignedTestConnection() {
        SpyHttp http = new SpyHttp(new Response(200, new HashMap<>(), ""));
        MojByt api = new MojByt(http, "123456", "crc_1234567");

        api.testConnection();

        Request lastRequest = http.lastRequest;

        Assert.assertTrue(
                lastRequest.getParams()
                        .containsKey("p24_sign")
        );
        Assert.assertEquals(
            "E010B36445FF2A0C712A4DBCEFC63922",
            lastRequest.getParams().get("p24_sign")
        );
    }
}
