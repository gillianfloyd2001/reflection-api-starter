package org.basecampcodingacademy.reflections.controllers;

import org.basecampcodingacademy.reflections.db.AnswerRepository;
import org.basecampcodingacademy.reflections.db.ResponseRepository;
import org.basecampcodingacademy.reflections.domain.Response;
import org.basecampcodingacademy.reflections.exception.ErrorMessageReflection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ResponseController {
    @Autowired
    public ResponseRepository responses;
    @Autowired
    public AnswerRepository answers;

    @PostMapping ("/reflections/{reflectionId}/responses")
    @ResponseStatus(HttpStatus.CREATED)
    public Response create(@PathVariable Integer reflectionId, @RequestBody Response response) {
        response.reflectionId = reflectionId;
        return responses.create(response);
    }

    @GetMapping("/reflections/{reflectionId}/responses")
    public List<Response> index(){
        return responses.all();
    }
    @ExceptionHandler ({ ErrorMessageReflection.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleErrorMessageReflection(ErrorMessageReflection ex) {
        var errorMap = new HashMap<String, String>();
        errorMap.put("error", "Reflection " + ex.reflectionId.toString() + " does not exist");
        return errorMap;
    }
}
