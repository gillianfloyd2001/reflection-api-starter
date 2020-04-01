package org.basecampcodingacademy.reflections.db;

import org.basecampcodingacademy.reflections.domain.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AnswerRepository {
    @Autowired
    public JdbcTemplate jdbc;

    public Answer create(Answer answer) {
        var sql = "INSERT INTO answers (question_id, response_id, content) VALUES (?, ?, ?) RETURNING id, response_id, question_id, content";
        return jdbc.queryForObject(
                sql,
                this::mapper,
                answer.questionId,
                answer.responseId,
                answer.content

        );
    }

    public List<Answer> findAllAnswer(Answer answer) {
        return jdbc.query(
                "SELECT * FROM answers WHERE response_id = ?",
                this::mapper,
                answer.responseId
        );
    }

    public Answer update(Answer answer) {
        return jdbc.queryForObject(
                "UPDATE answers SET content = ? WHERE id = ? RETURNING id, response_id, question_id, content",
                this::mapper, answer.content, answer.id);
    };

    public Answer one(Integer id) {
        return jdbc.queryForObject("SELECT * FROM answers WHERE id = ?", this::mapper, id);
    };

    public List<Answer> forResponse(Integer responseId) {
        return jdbc.query(
                "SELECT * FROM answers WHERE response_id = ?", this::mapper, responseId
        );
    };


    private Answer mapper(ResultSet resultSet, int i) throws SQLException {
        return new Answer(
                resultSet.getInt("id"),
                resultSet.getInt("question_id"),
                resultSet.getInt("response_id"),
                resultSet.getString("content")
        );
    };

}
