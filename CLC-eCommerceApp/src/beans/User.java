//Jacob Hushaw, Harold Berudez
//CST - 235, Jevon Jackson
//This is our own work.
package beans;

import java.security.Principal;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@ManagedBean
@ViewScoped
@XmlRootElement(name="User") 
public class User {

	@NotNull
	@Size(min=5, max=30)
	private String username;
	@NotNull
	@Size(min=5, max=30)
	private String password;
	@NotNull
	@Size(min=5, max=30)
	private String firstName;
	@NotNull
	@Size(min=5, max=30)
	private String lastName;
	
	public User() {
		
	}
	
	/*
	 * Non Default Constructor
	 */
	public User(String username, String password, String firstName, String lastName) {
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}


	///////Getters and Setters///////
	public String getUsername() {
		return this.username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return this.password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@PostConstruct
	public void init() {
		// Get the logged in Principle
		Principal principle= FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
		    if(principle == null)
		    {
		        setFirstName("Unknown");
		        setLastName("");
		    }
		    else
		    {
		        setFirstName(principle.getName());
		        setLastName("");
		    }
	}
	
	
}
