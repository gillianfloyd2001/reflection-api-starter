package org.basecampcodingacademy.reflections.domain;

import java.util.List;

public class Response {
    public Integer id;
    public String userUsername;
    public Integer reflectionId;
    public List<Object> answers;

    public Response() {};

    public  Response(Integer id, String userName, Integer reflectionId, List<Object> answers) {
        this.id = id;
        this.userUsername = userName;
        this.reflectionId = reflectionId;
        this.answers = answers;
    };
}
