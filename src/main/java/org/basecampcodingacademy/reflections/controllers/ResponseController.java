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

    @PostMapping ("/reflections/{reflection_id}/responses")
    @ResponseStatus(HttpStatus.CREATED)
    public Response create(@RequestBody Response response) {return responses.create(response);}

    @GetMapping("/reflections/{reflection_id}/responses")
    public List<Response> index() {return responses.all();}
}
