package edu.jproyo.dojos.contributorstest;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * The Class ServletConfig.
 */
public class ServletConfig extends SpringBootServletInitializer{
	
	/**
	 * Instantiates a new servlet config.
	 */
	public ServletConfig() { }
	
	/* (non-Javadoc)
	 * @see org.springframework.boot.context.web.SpringBootServletInitializer#configure(org.springframework.boot.builder.SpringApplicationBuilder)
	 */
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

}
