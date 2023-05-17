package com.sr.chatpanel.rest.exceptions;

import com.mysql.cj.xdevapi.JsonArray;
import jakarta.validation.ConstraintViolationException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mediatype.problem.Problem;
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


        final JSONArray jsonArray = new JSONArray();

        for (final var constraint : e.getFieldErrors()) {

            String field = constraint.getField();
            String message = constraint.getDefaultMessage();

            System.out.println(message);

            JSONObject jsonError = new JSONObject();
            jsonError.put("field", field);
            jsonError.put("violationMessage", message);
            jsonArray.put(jsonError);

        }

        JSONObject errorJsonEntity = jsonObject.put("errors", jsonArray);


        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .header(HttpHeaders.CONTENT_TYPE,
                        MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                .body(errorJsonEntity.toString());
    }
}
