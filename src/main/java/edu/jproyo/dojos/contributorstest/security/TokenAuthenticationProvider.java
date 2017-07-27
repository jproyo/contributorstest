package edu.jproyo.dojos.contributorstest.security;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;

/**
 * The Class TokenAuthenticationProvider.
 */
@Service
public class TokenAuthenticationProvider implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {
	

	/** The preauth key. */
	@Value("${auth.preauth.key}")
	private String preauthKey;

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.AuthenticationUserDetailsService#loadUserDetails(org.springframework.security.core.Authentication)
	 */
	@Override
	public UserDetails loadUserDetails(PreAuthenticatedAuthenticationToken request) throws UsernameNotFoundException {
		Optional<String> token = Optional.ofNullable((String)request.getPrincipal());
		if(!token.orElseThrow(()->new BadCredentialsException("Invalid token")).equals(preauthKey)){
			throw new BadCredentialsException("Invalid token or token expired");
		}
        return new User("ADMIN", "", Arrays.asList(new SimpleGrantedAuthority("ADMIN")));
    }


}
