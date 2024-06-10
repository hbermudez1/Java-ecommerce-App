//Jacob Hushaw, Harold Berudez
//CST - 235, Jevon Jackson
//This is our own work.
package businessServices;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;

import beans.User;
import dataAccessServices.UserDataAccess;

@Stateless
@ManagedBean
public class UserBusinessService {
	
	@EJB
	UserDataAccess uda;
    
	public UserBusinessService() {

	}
    
	/**
	 * sends user down to data access layer returns boolean
	 * 
	 * @param user
	 * @return boolean
	 */
	public boolean onLogin(User user) {
		//call find user
		User returned = uda.findUser(user);
		//if returned user != null then logged in
		if (returned.getUsername() == null && returned.getPassword() == null) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * Sends user to data access layer to be registered.
	 * 
	 * @param user
	 * @return boolean
	 */
	public boolean onRegister(User user) {
		//send user to data access return true or false for success insert.
		boolean returned = uda.create(user);
		return returned;
	}
	
	/**
	 * finds user from userDataAccess
	 * 
	 * @param id
	 * @return user
	 */
	public User findById(int id) {
		User u = uda.findById(id);
		if (u == null || u.getFirstName() == null) {
			u.setFirstName("this user does not exist.");
			return u;
		} else {
			return u;
		}
		
	}
    
}
