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
        return "index.html";
    }
    @GetMapping("/login")
    public String login() {
        return "login.html";
    }
    @GetMapping("/search")
    public String search() {
        return "SearchResults.html";
    }
    @GetMapping("/product")
    public String product() {
        return "ProductPage.html";
    }
    @GetMapping("/booking")
    public String booking() {
        return "BookingPage.html";
    }
}
