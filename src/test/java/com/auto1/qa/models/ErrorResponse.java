package com.auto1.qa.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "timestamp",
        "status",
        "error",
        "exception",
        "message",
        "trace",
        "path"
})
public class ErrorResponse {
    @JsonProperty("timestamp")
    private String timestamp;
    @JsonProperty("status")
    private int status;
    @JsonProperty("error")
    private String error;
    @JsonProperty("exception")
    private String exception;
    @JsonProperty("message")
    private String message;
    @JsonProperty("trace")
    private String trace;
    @JsonProperty("path")
    private String path;



    // Getter Methods
    @JsonProperty("timestamp")
    public String getTimestamp() {
        return timestamp;
    }
    @JsonProperty("status")
    public int getStatus() {
        return status;
    }
    @JsonProperty("error")
    public String getError() {
        return error;
    }
    @JsonProperty("exception")
    public String getException() {
        return exception;
    }
    @JsonProperty("message")
    public String getMessage() {
        return message;
    }
    @JsonProperty("trace")
    public String getTrace() {
        return trace;
    }
    @JsonProperty("path")
    public String getPath() {
        return path;
    }

    // Setter Methods
    @JsonProperty("timestamp")
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    @JsonProperty("status")
    public void setStatus(int status) {
        this.status = status;
    }
    @JsonProperty("error")
    public void setError(String error) {
        this.error = error;
    }
    @JsonProperty("exception")
    public void setException(String exception) {
        this.exception = exception;
    }
    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }
    @JsonProperty("trace")
    public void setTrace(String trace) {
        this.trace = trace;
    }
    @JsonProperty("path")
    public void setPath(String path) {
        this.path = path;
    }

}