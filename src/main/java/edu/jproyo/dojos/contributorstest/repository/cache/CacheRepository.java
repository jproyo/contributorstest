package edu.jproyo.dojos.contributorstest.repository.cache;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import edu.jproyo.dojos.contributorstest.model.Contributor;
import edu.jproyo.dojos.contributorstest.model.ContributorRequest;

/**
 * The Class GithubProxyRepository.
 */
@Service
public class CacheRepository {
	
	/** The cache. */
	private static Cache<ContributorRequest, List<Contributor>> cache = CacheBuilder.newBuilder()
																					.expireAfterWrite(5, TimeUnit.SECONDS)
																					.build();
	
	/**
	 * Contributors.
	 *
	 * @param request the request
	 * @return the list
	 */
	public Optional<List<Contributor>> contributors(ContributorRequest request) {
		return Optional.ofNullable(cache.getIfPresent(request));
	}

	/**
	 * Put.
	 *
	 * @param request the request
	 * @param result the result
	 */
	public void put(ContributorRequest request, List<Contributor> result) {
		cache.put(request, result);
	}

}
