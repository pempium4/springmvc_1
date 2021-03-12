package spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/first")
public class FirstController {

    @GetMapping("/hello")
    //получаем доступ ко всему объекту request :/
    public String helloPage(HttpServletRequest request){
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        System.out.println("Hello, " + firstName + " " + lastName);

        return "first/hello_page";
    }

    @GetMapping("/goodbye")
    //создаем лишь нужные getParams :)
    //404 если не передать параметры
    public String goodbyePage(@RequestParam("firstName") String firstName,
                              @RequestParam(value="lastName", required = false) String lastName){
        System.out.println("Goodbye, " + firstName + " " + lastName);

        return "first/goodbye_page";
    }
}
