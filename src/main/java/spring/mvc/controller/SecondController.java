package spring.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.mvc.DAO.PersonDAO;

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
}
