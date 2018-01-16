package pl.jkan.przelewy24;

import pl.jkan.support.http.HttpClient;
import pl.jkan.support.http.Request;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class MojByt {
    private HttpClient http;
    private String merchantId;
    private String crc;

    public MojByt(HttpClient http, String merchantId, String crc){

        this.http = http;
        this.merchantId = merchantId;
        this.crc = crc;
    }
    public void testConnection(){
        HashMap <String,String> params = new HashMap<>();
        params.put("p24_merchant_id","");
        params.put("p24_sign", generateMd5(
                String.format(
                    "%s|%s",
                    merchantId,
                    crc
                )
        ));
        try {
            http.send(Request.post(
                    " https://secure.przelewy24.pl/testConnection",
                    params,
                    new HashMap<>()
            ));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String generateMd5(String plaintext) {
        try {
            MessageDigest m= MessageDigest.getInstance("MD5");
            m.reset();
            m.update(plaintext.getBytes());
            byte[] digest = m.digest();
            BigInteger bigInt = new BigInteger(1,digest);
            String hashtext = bigInt.toString(16);
            while(hashtext.length() < 32 ){
                hashtext = "0"+hashtext;
            }
            return hashtext.toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            return "";
        }
    }
}
