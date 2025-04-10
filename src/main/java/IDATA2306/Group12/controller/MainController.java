package IDATA2306.Group12.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping("/")
    public String index() {
        return "index";
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/search")
    public String search() {
        return "search-results";
    }
    @GetMapping("/product")
    public String product() {
        return "product-Page";
    }
    @GetMapping("/booking")
    public String booking() {
        return "booking-page";
    }
    @GetMapping("/myPage")
    public String myPage() {
        return "MyPage.html";
    }

}
