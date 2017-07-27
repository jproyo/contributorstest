package edu.jproyo.dojos.contributorstest.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import edu.jproyo.dojos.contributorstest.model.Contributor;
import edu.jproyo.dojos.contributorstest.service.ContributorsService;

/**
 * The Class ContributorsApi.
 */
@RestController
@RequestMapping(value="/contributors")
public class ContributorsApi {
	
	/** The service. */
	@Autowired
	private ContributorsService service;
	
	/**
	 * Hello.
	 *
	 * @param city the city
	 * @param number the number
	 * @return the string
	 */
	@RequestMapping(value="/{city}/top/{number}", method=RequestMethod.GET, consumes="application/json")
	@ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<List<Contributor>> contributors(@PathVariable(required = true) String city, @PathVariable(required = true) Integer number){
		try {
			return new ResponseEntity<List<Contributor>>(service.contributors(city, number), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Contributor>>(HttpStatus.BAD_REQUEST);
		}
	}

}
