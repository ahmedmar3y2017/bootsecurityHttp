package rc.bootsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("index")
    public String index() {
        return "index";
    }

    @GetMapping("/403")
    public String error403() {
        return "error/403";
    }
}
