/*
 * Copyright 2007 Yusuke Yamamoto
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.socialhub.http;

import net.socialhub.http.HttpClientConfiguration.HttpClientDefaultConfiguration;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import static net.socialhub.http.RequestMethod.*;

/**
 * HTTP Client wrapper with handy request methods, ResponseListener mechanism
 *
 * @author Yusuke Yamamoto - yusuke at mac.com
 */
public class HttpClientWrapper implements Serializable {

    private final HttpClientConfiguration config;
    private final Map<String, String> requestHeaders;

    private HttpClient http;
    private HttpResponseListener httpResponseListener;


    public HttpClientWrapper() {
        this.config = new HttpClientDefaultConfiguration();
        requestHeaders = new HashMap<>();
        http = HttpClientFactory.getInstance(config);
    }

    public HttpClientWrapper(HttpClientConfiguration config) {
        this.config = config;
        requestHeaders = new HashMap<>();
        http = HttpClientFactory.getInstance(config);
    }

    public void shutdown() {
        http.shutdown();
    }

    protected HttpResponse request(HttpRequest req) throws HttpException {
        HttpResponse res;
        try {
            res = http.request(req);
            //fire HttpResponseEvent
            if (httpResponseListener != null) {
                httpResponseListener.httpResponseReceived(new HttpResponseEvent(req, res, null));
            }
        } catch (HttpException fe) {
            if (httpResponseListener != null) {
                httpResponseListener.httpResponseReceived(new HttpResponseEvent(req, null, fe));
            }
            throw fe;
        }
        return res;
    }

    public void setHttpResponseListener(HttpResponseListener listener) {
        httpResponseListener = listener;
    }

    // GET

    public HttpResponse get(String url) throws HttpException {
        return request(new HttpRequest(GET, url, null, this.requestHeaders));
    }

    public HttpResponse get(String url, HttpParameter[] parameters) throws HttpException {
        return request(new HttpRequest(GET, url, parameters, this.requestHeaders));
    }

    // POST

    public HttpResponse post(String url) throws HttpException {
        return request(new HttpRequest(POST, url, null, this.requestHeaders));
    }

    public HttpResponse post(String url, HttpParameter[] parameters) throws HttpException {
        return request(new HttpRequest(POST, url, parameters, this.requestHeaders));
    }

    public HttpResponse post(String url, HttpParameter[] parameters, Map<String, String> requestHeaders) throws HttpException {
        Map<String, String> headers = new HashMap<String, String>(this.requestHeaders);
        if (requestHeaders != null) headers.putAll(requestHeaders);
        return request(new HttpRequest(POST, url, parameters, headers));
    }

    // DELETE

    public HttpResponse delete(String url) throws HttpException {
        return request(new HttpRequest(DELETE, url, null, this.requestHeaders));
    }

    public HttpResponse delete(String url, HttpParameter[] parameters) throws HttpException {
        return request(new HttpRequest(DELETE, url, parameters, this.requestHeaders));
    }

    // HEAD

    public HttpResponse head(String url) throws HttpException {
        return request(new HttpRequest(HEAD, url, null, this.requestHeaders));
    }

    public HttpResponse head(String url, HttpParameter[] parameters) throws HttpException {
        return request(new HttpRequest(HEAD, url, parameters, this.requestHeaders));
    }

    // PUT

    public HttpResponse put(String url) throws HttpException {
        return request(new HttpRequest(PUT, url, null, this.requestHeaders));
    }

    public HttpResponse put(String url, HttpParameter[] parameters) throws HttpException {
        return request(new HttpRequest(PUT, url, parameters, this.requestHeaders));
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HttpClientWrapper that = (HttpClientWrapper) o;

        if (!http.equals(that.http)) return false;
        if (!requestHeaders.equals(that.requestHeaders)) return false;
        if (!config.equals(that.config)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = config.hashCode();
        result = 31 * result + http.hashCode();
        result = 31 * result + requestHeaders.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "HttpClientWrapper{" +
                "config=" + config +
                ", http=" + http +
                ", requestHeaders=" + requestHeaders +
                ", httpResponseListener=" + httpResponseListener +
                '}';
    }
}
