package spring.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import spring.mvc.DAO.PersonDAO;
import spring.mvc.model.Person;

import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class SecondController {

    private final PersonDAO personDAO;

    @Autowired
    public SecondController(PersonDAO personDAO) {
        this.personDAO = personDAO;
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
    @GetMapping("/{id}")
    public String index(@PathVariable("id") int id,
                        Model model){
        model.addAttribute("person", personDAO.index(id));
        return "/second/index";
    }
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model){
        model.addAttribute("person", personDAO.index(id));
        return "/second/edit";
    }

    @PostMapping
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "second/new";

        personDAO.save(person);
        return "redirect:/people";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "second/edit";

        personDAO.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personDAO.delete(id);
        return "redirect:/people";
    }
}
