package IDATA2306.Group12.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping("/")
    public String index() {
        return "index";
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
        return "my-page.html";
    }
    @GetMapping("/register")
    public String register() {
        return "register.html";
    }

    @GetMapping("/contactInformation")
    public String contactInformation() {
        return "contact-information.html";
    }
    @GetMapping("/favourites")
    public String favourites() {
        return "favourites.html";
    }
    @GetMapping("/terms-and-conditions")
    public String termsAndConditions() {return "terms-and-conditions.html";}
    @GetMapping("/privacy-policy")
    public String privacyPolicy() {return "privacy-policy.html";}
}
