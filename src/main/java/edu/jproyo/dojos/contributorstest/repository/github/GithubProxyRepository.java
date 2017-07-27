package edu.jproyo.dojos.contributorstest.repository.github;

import java.io.IOException;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.jcabi.github.Github;
import com.jcabi.github.RtGithub;
import com.jcabi.github.Search.Order;
import com.jcabi.github.User;

import edu.jproyo.dojos.contributorstest.model.Contributor;
import edu.jproyo.dojos.contributorstest.model.ContributorRequest;

/**
 * The Class GithubProxyRepository.
 */
@Service
public class GithubProxyRepository {
	
	/**
	 * Contributors.
	 *
	 * @param request the request
	 * @return the list
	 */
	public List<Contributor> contributors(ContributorRequest request) {
		Github github = new RtGithub();
		Spliterator<User> spliterator;
		try {
			spliterator = github.search().users(String.format("location:%s repos:>%d", request.getCity().toLowerCase(), new Double(Math.abs(request.getNumber()*0.2)).intValue()), "", Order.DESC).spliterator();
			return StreamSupport.stream(spliterator, false).limit(request.getNumber()).map(t -> {
				try {
					return new Contributor(t.login());
				} catch (IOException e) {
				}
				return null;
			}).collect(Collectors.toList());
		} catch (Exception e) {
			throw new RuntimeException("Error searching users");
		}
	}

}
