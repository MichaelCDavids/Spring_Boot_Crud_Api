package net.code.ezingo.service;

import net.code.ezingo.dao.PersonDao;
import net.code.ezingo.model.Person;

public class PersonService {

    private final PersonDao personDao;

    public PersonService(PersonDao personDao) {
        this.personDao = personDao;
    }

    public int addPerson(Person person){
        return personDao.insertPerson(person);
    }
}
