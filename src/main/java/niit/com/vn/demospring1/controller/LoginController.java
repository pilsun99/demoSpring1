package niit.com.vn.demospring1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("LoginController")
public class LoginController {
@GetMapping("/login")
    public String login(){
    return "user/login";
    }

    @PostMapping("/do-login")
    public void doLogin(@RequestParam("username") String username,
                        @RequestParam("password") String password) {
        //xử lý đăng nhập
        //lấy dữ liệu mà người dùng gửi lên
        System.out.println("username: " + username);
        System.out.println("password: " + password);
    }
}
