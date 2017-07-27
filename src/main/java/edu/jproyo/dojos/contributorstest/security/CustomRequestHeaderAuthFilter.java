package edu.jproyo.dojos.contributorstest.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;

/**
 * The Class CustomRequestHeaderAuthFilter.
 */
public class CustomRequestHeaderAuthFilter extends RequestHeaderAuthenticationFilter{
	
	/**
	 * Instantiates a new custom request header auth filter.
	 */
	public CustomRequestHeaderAuthFilter() {
		this.setPrincipalRequestHeader("Authorization");
		this.setExceptionIfHeaderMissing(false);
	}
	
    /* (non-Javadoc)
     * @see org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter#unsuccessfulAuthentication(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.AuthenticationException)
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException failed) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }

}
