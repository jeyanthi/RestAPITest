package com.paypal.restapp.resources;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class Greetings {
	@RequestMapping(value = "/{param}", method = RequestMethod.GET)
	  public String printMessage(@PathVariable("param") String msg) {
	    String result = "Hello " + msg + "!";
	    return result;
	  }
}
