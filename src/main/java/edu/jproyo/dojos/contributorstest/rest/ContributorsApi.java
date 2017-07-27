package edu.jproyo.dojos.contributorstest.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/contributors")
public class ContributorsApi {
	
	@RequestMapping(value="/hello", method=RequestMethod.GET, consumes="application/json")
	@ResponseStatus(value = HttpStatus.OK)
	public String hello(){
		return "hello";
	}

}
