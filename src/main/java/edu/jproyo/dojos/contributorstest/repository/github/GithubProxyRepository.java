package edu.jproyo.dojos.contributorstest.repository.github;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.jproyo.dojos.contributorstest.model.Contributor;
import edu.jproyo.dojos.contributorstest.model.ContributorRequest;

/**
 * The Class GithubProxyRepository.
 */
@Service
public class GithubProxyRepository {
	
	/** The mapper. */
	private ObjectMapper mapper = new ObjectMapper();
	
	/**
	 * Contributors.
	 *
	 * @param request the request
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public List<Contributor> contributors(ContributorRequest request) {
		boolean morePages = true;
		int page = 1;
		List<Contributor> result = new ArrayList<>();
		do {
			List<Contributor> contributors = null;
			Map<String,Object> response = new HashMap<>();
			try {
				CloseableHttpClient client = HttpClientBuilder.create().build();
				CloseableHttpResponse execute = client.execute(new HttpGet(url(request, page)));
				if(execute.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
					String json = EntityUtils.toString(execute.getEntity());
					response = mapper.readValue(json, new TypeReference<Map<String, Object>>() {});
					contributors = ((List<Map<String,Object>>)response.get("items")).stream().map(m -> new Contributor((String) m.get("login"))).collect(Collectors.toList());
				}
				if(contributors != null && !contributors.isEmpty()){
					int limit = request.getNumber()-result.size();
					result.addAll(contributors.stream().limit(limit).collect(Collectors.toList()));
				}
				Integer totalCount = (Integer) Optional.ofNullable(response.get("total_count")).orElse(0);
				Integer pages = totalCount/request.getNumber();
				morePages = page++ < pages;
			} catch (Exception e) {
				throw new RuntimeException("Error searching users");
			}
		}while(result.size() < request.getNumber() && morePages);
		return result;
	}

	/**
	 * Url.
	 *
	 * @param request the request
	 * @param page the page
	 * @return the uri
	 * @throws URISyntaxException the URI syntax exception
	 */
	private URI url(ContributorRequest request, Integer page) throws URISyntaxException {
		URIBuilder uriBuilder = new URIBuilder("https://api.github.com/search/users");
		uriBuilder.addParameter("q", String.format("location:%s repos:>%d", request.getCity().toLowerCase(), new Double(Math.abs(request.getNumber()*0.2)).intValue()));
		uriBuilder.addParameter("order", "desc");
		uriBuilder.addParameter("per_page", request.getNumber().toString());
		uriBuilder.addParameter("page", page.toString());
		return uriBuilder.build();
	}

}
