package opgave01.controller;

import opgave01.models.Person;
import opgave01.models.Role;
import opgave01.storage.EaaaFileStorage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Controller {
    EaaaFileStorage eaaaStorage;

    public Controller() {
        this.eaaaStorage = new EaaaFileStorage();
    }

    /**
     *
     * @param role
     * @return List<Person> where all person has the given role
     */
    public List<Person> filter(Role role) {
        List<Person> persons = eaaaStorage.getPeople();
        List<Person> filteredList = new ArrayList<>();
        for (Person person : persons) {
            if (person.getRole() == role) {
                filteredList.add(person);
            }
        }
        return filteredList;
    }

    /**
     *
     * @return all persons
     */
    public List<Person> getPeople() {
        return this.eaaaStorage.getPeople();
    }

    /**
     * Adds a new person
     * @param person
     */
    public void addPerson(Person person) {
        this.eaaaStorage.addPerson(person);
    }

    /**
     * Persists all people
     */
    public void save() {
        this.eaaaStorage.save();
    }
}
