package net.code.ezingo.api;

import net.code.ezingo.model.Person;
import net.code.ezingo.service.PersonService;

public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    public void addPerson(Person person){
        personService.addPerson(person);
    }
}
