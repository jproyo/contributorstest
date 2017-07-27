package edu.jproyo.dojos.contributorstest.model;

/**
 * The Class Contributor.
 */
public class Contributor {
	
	/** The login. */
	private String login;
	
	/**
	 * Instantiates a new contributor.
	 */
	public Contributor() {
	}
	
	/**
	 * Instantiates a new contributor.
	 *
	 * @param login the login
	 */
	public Contributor(String login) {
		this.login = login;
	}

	/**
	 * Gets the login.
	 *
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Sets the login.
	 *
	 * @param login the new login
	 */
	public void setLogin(String login) {
		this.login = login;
	}
	
}
