package niit.com.vn.demospring1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("homeController")
public class HomeController {
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("name", "Nguyen Văn A");
        model.addAttribute("age", "20");
        User user = new User();
        user.name = "Nguyen Van B";
        user.age = 30;
        model.addAttribute("user", user);
        model.addAttribute("weather", "sunny");

        String[] names = {"Nguyen VAn A","Nguyen Van B", "Nguyen Van C"};
        model.addAttribute("names",names);
        return "index2";
    }

    @GetMapping("/about")
    public String about() {
        return "test/about";
    }

/*    @PostMapping("/do-login")
    public String doLogin() {
        return "index";
    }

    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    public String login() {
        return "login";
    }*/
}
