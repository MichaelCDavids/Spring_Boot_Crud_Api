package net.code.ezingo.dao;

import net.code.ezingo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository("postgres")
public class PersonDataAccessService implements PersonDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public int insertPerson(UUID id, Person person) {
        String sql ="insert into person (id,name) values (?,?)";
        UUID newId = UUID.randomUUID();
        String name = person.getName();
        jdbcTemplate.update(sql,newId,name);
        return 0;
    }

    @Override
    public List<Person> selectAllPeople() {
        final String sql = "select id, name from person";
        return jdbcTemplate.query(sql, (resultSet, i) -> new Person(UUID.fromString(resultSet.getString("id")), resultSet.getString("name")));
    }


    @Override
    public Optional<Person> selectPersonById(UUID id) {
        final String sql = "select id, name from person where id = ?";
        Person person = jdbcTemplate.queryForObject(
                sql,
                new Object[]{id},
                (resultSet, i) -> {
                    UUID personId = UUID.fromString(resultSet.getString("id"));
                    String name = resultSet.getString("name");
                    return new Person(personId, name);
                });
        return Optional.ofNullable(person);
    }

    @Override
    public int deletePersonById(UUID id) {
        final String sql = "delete from person where id = ?";
        jdbcTemplate.update(sql,id);
        return 0;
    }

    @Override
    public int updatePersonById(UUID id, Person person) {
        final String sql = "update person set  name=? where id = ?";
        String name = person.getName();
        jdbcTemplate.update(sql,name,id);
        return 0;
    }
}
