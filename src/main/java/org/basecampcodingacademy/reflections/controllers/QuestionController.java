package org.basecampcodingacademy.reflections.controllers;

import org.basecampcodingacademy.reflections.db.QuestionRepository;
import org.basecampcodingacademy.reflections.domain.Question;
import org.basecampcodingacademy.reflections.domain.Reflection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class QuestionController {
    @Autowired

    public QuestionRepository questions;

    @PostMapping("/reflections/{reflectionId}/questions")
    public Question create(@RequestBody Question question, @PathVariable Integer reflectionId) {
        question.reflectionId = reflectionId;
        return questions.create(question);
    }
    @GetMapping("/reflections/{reflectionId}/questions")
    public List<Question> index() {
        return questions.all();
    }

    @PatchMapping("/reflections/{reflectionId}/questions/{id}")
    public Question update(@PathVariable Integer id, @RequestBody Question question) {
        question.id = id;
        return questions.update(question);
    };

    @DeleteMapping("/reflections/{reflectionId}/questions/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        questions.delete(id);
    };
}
