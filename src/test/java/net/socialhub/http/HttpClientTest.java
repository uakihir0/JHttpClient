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

    @Test
    public void testJsonRequest() {

        try {
            HttpRequestBuilder builder = new HttpRequestBuilder();
            builder.target("https://misskey.io/api/");
            builder.path("username/available");
            builder.json("{\"username\": \"cccc\"}");

            HttpResponse response = builder.post();
            System.out.println(response.asString());

        } catch (HttpException e) {
            e.printStackTrace();
        }
    }
}
