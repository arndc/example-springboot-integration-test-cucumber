package me.arndc.example.testing.cucumber.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/hello")
public class HelloRestController {

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<String> greetWorld(@RequestParam(name = "greeter", defaultValue = "Cucumber") final String name,
                                             @RequestParam(name = "allowed", defaultValue = "true") final boolean isAllowed) {
        if (!isAllowed) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        return ResponseEntity.ok("Hello World! My name is " + name + ".");
    }
}
