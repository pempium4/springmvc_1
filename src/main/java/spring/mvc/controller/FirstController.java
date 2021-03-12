package spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/first")
public class FirstController {

    @GetMapping("/hello")
    //получаем доступ ко всему объекту request :/
    public String helloPage(HttpServletRequest request, Model model){
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

//        System.out.println("Hello, " + firstName + " " + lastName);
        model.addAttribute("message", "Hello, " + firstName + " " + lastName);
        return "first/hello_page";
    }

    @GetMapping("/goodbye")
    //создаем лишь нужные getParams :)
    //404 если не передать параметры
    public String goodbyePage(@RequestParam("firstName") String firstName,
                              @RequestParam(value="lastName", required = false) String lastName,
                              Model model){
//        System.out.println("Goodbye, " + firstName + " " + lastName);
        model.addAttribute("message", "Goodbye, " + firstName + " " + lastName);

        return "first/goodbye_page";
    }

    @GetMapping("/calculator")
    public String calculatorPage(@RequestParam("a") int var1,
                                 @RequestParam("b") int var2,
                                 @RequestParam("action") String action,
                                 Model model){
        double result = 0;
        switch (action){
            case "multiplication":
                result = var1 * var2;
                break;
            case "addition":
                result = var1 + var2;
                break;
            case "subtraction":
                result = var1 - var2;
                break;
            case "division":
                result = var1 / (double)var2;
        }
        model.addAttribute("result", result);
        return "first/calculator_page";
    }
}
