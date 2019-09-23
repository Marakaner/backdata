package net.alexander.backdata.database;

public class FailedRequest implements DatabaseComponent {

    private String message;
    private RequestError requestError;

    public FailedRequest(String message, RequestError requestError) {
        this.message = message;
        this.requestError = requestError;
    }

    public RequestError getRequestError() {
        return requestError;
    }

    public String getMessage() {
        return message;
    }
}
