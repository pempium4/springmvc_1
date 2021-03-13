package spring.mvc.DAO;

import org.springframework.stereotype.Component;
import spring.mvc.model.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int ID;
    private List<Person> personList;

    public PersonDAO() {
        personList = new ArrayList<>();
        personList.add(new Person(++ID, "Tom"));
        personList.add(new Person(++ID, "Jenn"));
        personList.add(new Person(++ID, "Edie"));
        personList.add(new Person(++ID, "Anna"));
    }

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
}
