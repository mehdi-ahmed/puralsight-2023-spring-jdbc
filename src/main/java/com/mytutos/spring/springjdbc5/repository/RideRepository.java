package com.mytutos.spring.springjdbc5.repository;

import com.mytutos.spring.springjdbc5.model.Ride;
import com.mytutos.spring.springjdbc5.repository.util.RideRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RideRepository {

    private final JdbcTemplate jdbcTemplate;

    public RideRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Ride> getRides() {
        return jdbcTemplate.query("SELECT * from ride", new RideRowMapper());
    }

    public Ride createRide(Ride ride) {

        // If you don't need to retrieve the object you just created.

        /*int result = jdbcTemplate.update("INSERT INTO Ride (name, duration) values (?,?)",
                ride.getName(), ride.getDuration());*/


        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
        List<String> columns = new ArrayList<>();
        columns.add("name");
        columns.add("duration");
        insert.setTableName("ride");
        insert.setColumnNames(columns);

        Map<String, Object> data = new HashMap<>();
        data.put("name", ride.getName());
        data.put("duration", ride.getDuration());

        insert.setGeneratedKeyName("id");

        Number id = insert.executeAndReturnKey(data);
        return getRide(id.intValue());
    }

    public Ride getRide(Integer id) {
        return jdbcTemplate.queryForObject("SELECT * FROm ride WHERE id = ?", new RideRowMapper(), id);
    }

    public Ride updateRide(Ride ride) {
        jdbcTemplate.update("UPDATE ride SET name = ?, duration = ? WHERE id = ?",
                ride.getName(), ride.getDuration(), ride.getId());
        return ride;
    }

    public void updateRides(List<Object[]> pairs) {
        jdbcTemplate.batchUpdate("UPDATE ride SET ride_date = ? WHERE id = ?", pairs);
    }

    public void deleteRide(Integer id) {

        // jdbcTemplate.update("DELETE FROM ride WHERE id = ?", id);

        // Alternative way with NamedParameterJdbcTemplate
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);

        // Useful if you have a bunch of parameters in a big table that you want to update. The Map does the mapping
        namedParameterJdbcTemplate.update("DELETE FROM ride WHERE id = :id", paramMap);

    }
}
