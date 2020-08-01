package net.code.ezingo.dao;

import net.code.ezingo.model.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDao {

    /**
     * Inserts a person into the database
     * @param id -  unique user id for person
     * @param person - person to be added to database
     * @return int - indicates if user was added to database
     */
    int insertPerson(UUID id, Person person);

    /**
     * The insertPerson methods adds a user to the database generating a UUID for each Person
     * @param person - person to be inserted into the database
     * @return integer - indicates if user was added to database
     */
    default int insertPerson(Person person){
        UUID id = UUID.randomUUID();
        return insertPerson(id, person);
    }
    List<Person> selectAllPeople();
    Optional<Person> selectPersonById(UUID id);
    int deletePersonById(UUID id);
    int updatePersonById(UUID id, Person person);
}
