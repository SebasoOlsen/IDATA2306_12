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
        return "SearchResults";
    }
    @GetMapping("/product")
    public String product() {
        return "ProductPage";
    }
    @GetMapping("/booking")
    public String booking() {
        return "BookingPage";
    }
}
