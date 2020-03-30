package org.basecampcodingacademy.reflections.controllers;

import org.basecampcodingacademy.reflections.db.ResponseRepository;
import org.basecampcodingacademy.reflections.domain.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ResponseController {
    @Autowired

    public ResponseRepository responses;

    @PostMapping ("/reflections/{reflectionId}/responses")
    @ResponseStatus(HttpStatus.CREATED)
    public Response create(@PathVariable Integer reflectionId, @RequestBody Response response) {
        response.reflectionId = reflectionId;
        return responses.create(response);
    }

    @GetMapping("/reflections/{reflectionId}/responses")
    public List<Response> index() {return responses.all();}
}
