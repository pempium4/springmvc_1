package spring.mvc.DAO;

import org.springframework.stereotype.Component;
import spring.mvc.model.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int ID;
    private final List<Person> personList = new ArrayList<>();

    public Person index(int id){
        for (Person person:personList) {
            if(person.getId() == id){
                return person;
            }
        }
        return null;
        //return personList.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public List<Person> show(){
        return personList;
    }

    public void save(Person person){
        person.setId(++ID);
        personList.add(person);
    }

    public void update(int id, Person person){
        Person personToBeUpdated = index(id);
        personToBeUpdated.setName(person.getName());
    }
    public void delete(int id){
        Person person = index(id);
        personList.remove(person);
    }
}
