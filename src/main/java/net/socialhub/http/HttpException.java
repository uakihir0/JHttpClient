package net.socialhub.http;

public class HttpException extends Exception {

    private HttpResponse response;
    private String message;
    private int responseCode;


    public HttpException(String message, Exception e, int responseCode) {
        this.responseCode = responseCode;
        this.message = message;
        this.initCause(e);
    }

    public HttpException(String message, HttpResponse res) {
        this.message = message;
        this.response = res;
    }

    public HttpException(String message, Exception e) {
        this.message = message;
        this.initCause(e);
    }

    //<editor-fold desc="// Getter&Setter">
    public HttpResponse getResponse() {
        return response;
    }

    public void setResponse(HttpResponse response) {
        this.response = response;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    //</editor-fold>
}
