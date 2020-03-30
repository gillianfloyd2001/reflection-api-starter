package org.basecampcodingacademy.reflections.db;

import org.basecampcodingacademy.reflections.domain.Question;
import org.basecampcodingacademy.reflections.domain.Reflection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class QuestionRepository {
    @Autowired
    public JdbcTemplate jdbc;

    public List<Question> all() {
        return jdbc.query("SELECT * FROM questions ", this::mapper);
    }

    public Question create(Question question) {
        var sql = "INSERT INTO questions (prompt, reflection_id) VALUES (?, ?) RETURNING *";
        return jdbc.queryForObject(
                sql,
                this::mapper,
                question.prompt,
                question.reflectionId
                );
    }
    public Question update(Question question) {
        return jdbc.queryForObject(
                "UPDATE questions SET prompt = ? WHERE id = ? RETURNING *",
                this::mapper, question.prompt, question.id);
    }

    public void delete(Integer id) {
        jdbc.query("DELETE FROM questions WHERE id = ? RETURNING *", this::mapper, id);
    }

    public List<Question> forReflection(Integer reflectionId) {
        return jdbc.query(
                "SELECT * FROM questions WHERE reflection_id = ?",
                this::mapper,
                reflectionId
        );
    }

    private Question mapper(ResultSet resultSet, int i) throws SQLException {
        return new Question(
                resultSet.getInt("id"),
                resultSet.getString("prompt"),
                resultSet.getInt("reflection_id")
        );
    }
}
