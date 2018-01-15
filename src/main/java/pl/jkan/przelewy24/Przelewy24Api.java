package pl.jkan.przelewy24;

import pl.jkan.support.http.HttpClient;
import pl.jkan.support.http.Request;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class Przelewy24Api {

    private HttpClient client;
    private String merchanId;
    private String crc;

    public Przelewy24Api(HttpClient client, String merchanId, String crc) {
        this.client = client;
        this.merchanId = merchanId;
        this.crc = crc;
    }

    public void registerTransaction(RegisterTransactionData data) {
        HashMap<String, String> params = new HashMap<>();
        params.put("p24_merchant_id", "");
        params.put("p24_pos_id", "");

        params.put("p24_session_id", "");
        params.put("p24_amount", "");
        params.put("p24_currency", "");
        params.put("p24_description", "");
        params.put("p24_email", "");
        params.put("p24_country", "");
        params.put("p24_url_return", "");
        params.put("p24_api_version", "");
        params.put("p24_sign", generateMd5Sign(data));

        try {
            client.send(Request.post("trnRegister", params, new HashMap<>()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String generateMd5Sign(RegisterTransactionData data) {
        String toBeHashed = String.format(
            "%s|%s|%s|%s|%s",
                data.getSessionId(),
                merchanId,
                data.getAmount(),
                data.getCurrency(),
                crc
        );
        try {
            return hashMd5(toBeHashed).toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            return "";
        }
    }

    private String hashMd5(String toBeHashed) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(toBeHashed.getBytes());
        BigInteger number = new BigInteger(1, messageDigest);
        String hashtext = number.toString(16);

        while (hashtext.length() < 32) {
            hashtext = "0" + hashtext;
        }

        return hashtext;
    }

}
