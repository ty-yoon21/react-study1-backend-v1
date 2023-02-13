package com.portal.react.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JsonResponse<E> implements Serializable {

    @JsonIgnore
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    public static final String SUCCESS = "SUCCESS";
    @JsonIgnore
    public static final String ERROR = "ERROR";
    @JsonIgnore
    public static final String ERRORMSG = "ERRORMSG";
    @JsonIgnore
    public static final String PUBLISHER = "PUBLISHER";
    @JsonIgnore
    public static final String BODY = "BODY";

    // Response Status Code, 0 Success, 1 Error
    private int statusCode = 0;

    //If Success put the response Data here
    private E body = null;

    private String errorMsg = null;

    private String publisher = null;

    private JsonResponse() {}

    public JsonResponse(String publisher) {
        this();
        this.publisher = publisher;
    }

    public void success(E body) {
        this.statusCode = 0;
        this.body = body;
        this.errorMsg = null;
    }

    public void error(String errorMsg) {
        this.statusCode = 1;
        this.errorMsg = errorMsg;
        this.body = null;
    }

    public ResponseEntity<JsonResponse<E>> send() { return send(HttpStatus.OK); }

    public ResponseEntity<JsonResponse<E>> send(HttpStatus status) {
        return new ResponseEntity<JsonResponse<E>>(this, status);
    }

}
