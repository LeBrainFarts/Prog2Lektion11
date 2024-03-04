package Unittest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import opgave01.controller.Controller;
import opgave01.models.Person;
import opgave01.models.Role;
import opgave01.storage.EaaaFileStorage;

import javax.naming.ldap.Control;
import java.util.ArrayList;
import java.util.List;
public class Unittests {
    @Test
    public void testGetPeople() {
        Controller controller = new Controller();
        List<Person> people = controller.getPeople();
        Assertions.assertNotNull(people);
    }

    @Test
    public void testAddPerson() {
        Controller controller = new Controller();
        Person person = new Person("Jonas", Role.STUDENT);
        controller.addPerson(person);
        int actual = controller.getPeople().size();
        Assertions.assertEquals(controller.getPeople().size(),actual);
        Assertions.assertTrue(controller.getPeople().contains(person));
    }
}
