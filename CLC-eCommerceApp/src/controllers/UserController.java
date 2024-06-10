//Jacob Hushaw, Harold Berudez
//CST - 235, Jevon Jackson
//This is our own work.
package controllers;


import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import beans.User;
import businessServices.UserBusinessService;


@ManagedBean
@ViewScoped
public class UserController {

	@EJB
	UserBusinessService ubs;
	/**
	 * gets user from view, calls ubs.onLogin, returns success or fail view
	 * 
	 * @param user
	 * @return view
	 */
	public String onLogin(User user){
		
		//get user from  view
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", user);

		//create user with credentials
		User cred = new User(user.getUsername(),user.getPassword(),null,null);
		
		
		//check if user in database
		boolean loggedin = ubs.onLogin(cred);
		
		//set failed login message if not logged in
		String message= "";
		if (!loggedin) {
				message = "failed to Login.";
		}
		
		//add message to facesContext
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));

	    //return view if logged in or not.
			if (loggedin) {
				return "Home.xhtml?faces-redirect=true";
			} else {
				
				return "Login.xhtml";
			}
			    
	}
	
	/**
	 * Registers the user to the database from view.
	 * 
	 * @param user
	 * @return view
	 */
	public String onRegister(User user) {
		
		//get user from view
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", user);
		String message;		
		
		//create new user
		User newuser = new User(user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName());
		
		//create business Service
		//UserBusinessService ubs = new UserBusinessService();
		
		//call ubs.onRegister with the user
		boolean success = ubs.onRegister(newuser);
		
		//if successful register set message to success or fail
		if (success) {
			message = "registered successfully!"; 
		} else {
			message = "failed to register.";
		}
		//add message and return view.
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
		return "Login.xhtml";
	}
	
	public String onLogoff() {
		
		// Invalidate the Session to clear the security token
	    FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	        
	    // Redirect to a protected page (so we get a full HTTP Request) to get Login Page
	    return "TestResponse.xhtml?faces-redirect=true";
	}
	
}
