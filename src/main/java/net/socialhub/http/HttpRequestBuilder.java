package net.socialhub.http;

import net.socialhub.http.HttpClientConfiguration.HttpClientDefaultConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpRequestBuilder {

    private String host;

    private String path;

    private String mediaType;

    private HttpClientConfiguration config;

    private List<HttpParameter> params = new ArrayList<>();

    private Map<String, String> header = new HashMap<>();

    public HttpRequestBuilder() {
        this(new HttpClientDefaultConfiguration());
    }

    public HttpRequestBuilder(HttpClientConfiguration config) {
        this.config = config;
    }

    public HttpRequestBuilder target(String uri) {
        this.host = uri;
        return this;
    }

    public HttpRequestBuilder path(String path) {
        this.path = path;
        return this;
    }

    public HttpRequestBuilder query(String key, Object value) {
        return param(key, value);
    }

    public HttpRequestBuilder param(String key, Object value) {
        if (value instanceof File) {
            params.add(new HttpParameter(key, (File) value));
        } else {
            params.add(new HttpParameter(key, value.toString()));
        }
        return this;
    }

    public HttpRequestBuilder request(String mediaType) {
        this.mediaType = mediaType;
        return this;
    }

    public HttpRequestBuilder header(String key, String value) {
        this.header.put(key, value);
        return this;
    }

    public HttpResponse get() throws HttpException {
        return proceed(RequestMethod.GET);
    }

    public HttpResponse post() throws HttpException {
        return proceed(RequestMethod.POST);
    }

    private HttpResponse proceed(RequestMethod method) throws HttpException {
        assert host != null;

        HttpClient httpClient = new HttpClientImpl(config);

        if (mediaType != null) {
            header.put("Accept", mediaType);
        }

        HttpRequest request = new HttpRequest(method, getUrl(), //
                params.isEmpty() ? null : params.toArray(new HttpParameter[0]),
                header.isEmpty() ? null : header);
        return httpClient.request(request);
    }

    private String getUrl() {
        StringBuilder builder = new StringBuilder();
        builder.append(host);
        if (path != null) {
            builder.append(path);
        }
        return builder.toString();
    }
}
