package org.basecampcodingacademy.reflections.controllers;

import org.basecampcodingacademy.reflections.db.AnswerRepository;
import org.basecampcodingacademy.reflections.db.ReflectionRepository;
import org.basecampcodingacademy.reflections.db.ResponseRepository;
import org.basecampcodingacademy.reflections.domain.Response;
import org.basecampcodingacademy.reflections.exception.ErrorMessageReflection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@RestController
public class ResponseController {
    @Autowired
    public ResponseRepository responses;
    @Autowired
    public ReflectionRepository reflections;
    @Autowired
    public AnswerRepository answers;

    @PostMapping ("/reflections/{reflectionId}/responses")
    @ResponseStatus(HttpStatus.CREATED)
    public Response create(@RequestBody Response response,@PathVariable Integer reflectionId) throws ErrorMessageReflection {
        response.reflectionId = reflectionId;
        if (!Objects.isNull(reflections.find(reflectionId))) {
            return responses.create(response);
        } else {
            throw new ErrorMessageReflection(response.reflectionId);
        }
    }

    @GetMapping("/reflections/{reflectionId}/responses")
    public List<Response> index(@PathVariable Integer reflectionId, @RequestParam(defaultValue = "") String include) {
        var rs = responses.getResponses(reflectionId);
        if (include.equals("answers")) {
            for (var r : rs) {
                r.answers = answers.forResponse(r.id);
            }
        }
        return rs;
    }

    @ExceptionHandler ({ErrorMessageReflection.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HashMap<String, String> handleErrorMessageReflection(ErrorMessageReflection ex) {
        var errorMap = new HashMap<String, String>();
        errorMap.put("error", "Reflection " + ex.reflectionId.toString() + " does not exist");
        return errorMap;
    }
}
