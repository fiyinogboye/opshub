package dev.fiyin.opshub.config;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller public class PageController { @GetMapping("/") String home(){return "index";} @GetMapping("/login") String login(){return "login";} }
