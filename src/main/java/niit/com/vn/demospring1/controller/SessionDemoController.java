package niit.com.vn.demospring1.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("SessionDemoController")
public class SessionDemoController {
    @GetMapping("/set-session")
    public String setSession(HttpSession httpSession) {
        User user = new User();
        user.setName("Nguyen Thanh Luan");
        user.setAddress("QN");
        user.setAge(20);
        httpSession.setAttribute("user", user);
        return "set-cookie";
    }

    @GetMapping("/get-session")
    public String getSession(HttpSession httpSession, Model model) {
        User user = (User) httpSession.getAttribute("user");
        model.addAttribute("user", user);
        return "get-session";
    }

    @GetMapping("/delete-session")
    public String deleteSession(HttpSession httpSession) {
        httpSession.removeAttribute("user");
        return "set-cookie";
    }

}
