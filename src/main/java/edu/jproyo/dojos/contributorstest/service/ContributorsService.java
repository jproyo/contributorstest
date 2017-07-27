package edu.jproyo.dojos.contributorstest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.jproyo.dojos.contributorstest.model.Contributor;
import edu.jproyo.dojos.contributorstest.model.ContributorRequest;
import edu.jproyo.dojos.contributorstest.repository.cache.CacheRepository;
import edu.jproyo.dojos.contributorstest.repository.github.GithubProxyRepository;

/**
 * The Class ContributorsService.
 */
@Service
public class ContributorsService {
	
	/** The cache. */
	@Autowired
	private CacheRepository cache;
	
	/** The github. */
	@Autowired
	private GithubProxyRepository github;

	/**
	 * Contributors.
	 *
	 * @param city the city
	 * @param number the number
	 * @return the list
	 */
	public List<Contributor> contributors(String city, Integer number) {
		ContributorRequest request = new ContributorRequest(city, number);
		List<Contributor> result = cache.contributors(request).orElse(github.contributors(request));
		if(result != null && !result.isEmpty()) cache.put(request, result);
		return result;
	}
	
	

}
