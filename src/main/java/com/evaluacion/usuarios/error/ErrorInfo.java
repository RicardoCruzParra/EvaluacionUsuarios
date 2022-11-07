package com.evaluacion.usuarios.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class ErrorInfo
{
    @JsonProperty("message")
    private String message;
    @JsonProperty("status_code")
    private int statusCode;
    @JsonProperty("uri")
    private String uriRequested;

    public ErrorInfo(int statusCode, String message, String uriRequested)
    {
        this.message = message;
        this.statusCode = statusCode;
        this.uriRequested = uriRequested;
    }
}