package spring.mvc.DAO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import spring.mvc.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int ID;

//    @Value("${database.URL}")
    private static String URL = "jdbc:mysql://localhost:3306/users_db";
//    @Value("${database.USER}")
    private static String USER = "root";
//    @Value("${database.PASSWORD}")
    private static String PASSWORD = "root";

    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Person index(int id){
//        for (Person person:personList) {
//            if(person.getId() == id){
//                return person;
//            }
//        }
        return null;
        //return personList.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public List<Person> show(){
        List<Person> personList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM person";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()){
                Person person = new Person();
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setEmail(resultSet.getString("email"));
                person.setAge(resultSet.getInt("age"));

                personList.add(person);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return personList;
    }

    public void save(Person person){
        try {
            Statement statement = connection.createStatement();
            String SQL = "INSERT INTO Person VALUES(" + ++ID + ",'"
                    + person.getName() +
                    "'," + person.getAge() + ",'"
                    + person.getEmail() + "')";
            statement.executeUpdate(SQL);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void update(int id, Person person){
//        Person personToBeUpdated = index(id);
//        personToBeUpdated.setName(person.getName());
//        personToBeUpdated.setAge(person.getAge());
//        personToBeUpdated.setEmail(person.getEmail());
    }
    public void delete(int id){
//        Person person = index(id);
//        personList.remove(person);
    }
}
