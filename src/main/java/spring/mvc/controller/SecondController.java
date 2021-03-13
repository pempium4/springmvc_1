package spring.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.mvc.DAO.PersonDAO;
import spring.mvc.model.Person;

@Controller
@RequestMapping("/people")
public class SecondController {

    private final PersonDAO personDAO;

    @Autowired
    public SecondController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping("/{id}")
    public String index(@PathVariable("id") int id,
                        Model model){
        model.addAttribute("person", personDAO.index(id));
        return "/second/index";
    }
    @GetMapping
    public String show(Model model){
        model.addAttribute("people", personDAO.show());
        return "second/show";
    }
    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person){
        return "/second/new";
    }
    @PostMapping
    public String create(@ModelAttribute("person") Person person){
        personDAO.save(person);
        return "redirect:/people";
    }
}
