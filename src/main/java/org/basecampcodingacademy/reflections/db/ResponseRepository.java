package org.basecampcodingacademy.reflections.db;

import org.basecampcodingacademy.reflections.domain.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ResponseRepository {
    @Autowired
    public JdbcTemplate jdbc;

    public List<Response> getResponses(Integer reflectionId) {
        return jdbc.query("SELECT * FROM responses WHERE reflection_id = ?", this::mapper, reflectionId);
    }
    public Response create(Response response) {
        var sql = "INSERT INTO responses (username, reflection_id) VALUES (?, ?) RETURNING *";
        return jdbc.queryForObject(sql, this::mapper, response.userUsername, response.reflectionId);
    };

    private Response mapper(ResultSet resultSet, int i) throws SQLException {
        return new Response(
                resultSet.getInt("id"),
                resultSet.getString("username"),
                resultSet.getInt("reflection_id"),
                null
        );
    };
}
