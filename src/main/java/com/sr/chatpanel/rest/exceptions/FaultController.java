package com.sr.chatpanel.rest.exceptions;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class FaultController {
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    ResponseEntity<?> PNFEHandler(MethodArgumentNotValidException e) {

        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", "Validation errors");

        //final JSONArray jsonArray = new JSONArray();
        JSONObject errors = new JSONObject();

        for (final var constraint : e.getFieldErrors()) {

            String field = constraint.getField();
            String message = constraint.getDefaultMessage();

            // System.out.println(message);

            JSONArray jsonError = new JSONArray();
            jsonError.put(message);
            //jsonError.put("violationMessage", message);
            errors.put(field, jsonError);

        }

        JSONObject errorJsonEntity = jsonObject.put("errors", errors);


        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .header(HttpHeaders.CONTENT_TYPE,
                        MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                .body(errorJsonEntity.toString());
    }

    @ResponseBody
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    ResponseEntity<?> PNFEHandler(UnauthorizedException e) {

        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", "Unauthorized.");

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .header(HttpHeaders.CONTENT_TYPE,
                        MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                .body(jsonObject.toString());
    }

    @ResponseBody
    @ExceptionHandler(ActionImpossible.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    ResponseEntity<?> PNFEHandler(ActionImpossible e) {

        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", "Action impossible.");

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .header(HttpHeaders.CONTENT_TYPE,
                        MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                .body(jsonObject.toString());
    }
}
