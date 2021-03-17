package spring.mvc.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import spring.mvc.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {

    List<Person> personList;

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> show(){
        return jdbcTemplate.query("SELECT * FROM person", new PersonMapper());
    }

    public Person index(int id){
        //возвращает personList или null
        for (Person person: jdbcTemplate.query("SELECT * FROM person WHERE id=?", new Object[]{id}, new PersonMapper())) {
            if(person.getId() == id){
                return person;
            }
        }
//        return jdbcTemplate.query("SELECT * FROM person WHERE id=?", new Object[]{id}, new PersonMapper())
//                .stream().findAny().orElse(null);
        return null;
    }

    public void save(Person person){

    }

    public void update(int id, Person person){

    }
    public void delete(int id){

    }
}
