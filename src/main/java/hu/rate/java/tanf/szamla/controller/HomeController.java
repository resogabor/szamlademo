package hu.rate.java.tanf.szamla.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by resog on 2017. 02. 21..
 */
@Controller
public class HomeController {
    @RequestMapping("/")
    public String index(){
        return "index.html";
    }
}
