package spring.mvc.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import spring.mvc.model.Person;

import java.util.List;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> show(){
        //не обязательно писать свой RowMapper. Можно использовать существующий
        return jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person index(int id){
        //возвращает personList или null
        for (Person person: jdbcTemplate.query("SELECT * FROM person WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))) {
            if(person.getId() == id){
                return person;
            }
        }
//        return jdbcTemplate.query("SELECT * FROM person WHERE id=?", new Object[]{id}, new PersonMapper())
//                .stream().findAny().orElse(null);
        return null;
    }

    public void save(Person person){
        jdbcTemplate.update("INSERT INTO person (name, age, email) VALUES(?, ?, ?)", person.getName(), person.getAge(), person.getEmail());
    }

    public void update(int id, Person person){
        jdbcTemplate.update("UPDATE person SET name=?, age=?, email=? WHERE id=?", person.getName(), person.getAge(), person.getEmail(), id);
    }
    public void delete(int id){
        jdbcTemplate.update("DELETE FROM person WHERE id=?", id);
    }
}
