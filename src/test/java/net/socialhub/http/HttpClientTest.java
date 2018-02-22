package net.socialhub.http;

import org.junit.Test;

public class HttpClientTest {

    @Test
    public void testHttpClient() {

        try {
            HttpClientWrapper clientWrapper = new HttpClientWrapper();
            HttpResponse response = clientWrapper.get("https://www.google.co.jp/");
            System.out.println(response.asString());

        } catch (HttpException e) {
            e.printStackTrace();
        }
    }
}
