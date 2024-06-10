//Jacob Hushaw, Harold Berudez
//CST - 235, Jevon Jackson
//This is our own work.
package dataAccessServices;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;

import beans.User;
import dataAccessServices.DataAccessInterface;

@Stateless
@Local(DataAccessInterface.class)
@LocalBean
@ManagedBean
public class UserDataAccess implements DataAccessInterface<User> {

	/**
	 * finds user from database based of username and password
	 * 
	 * @param user
	 * @return User
	 */
	public User findUser(User user) {
		//create database credentials
		Connection conn = null;
		String url = "jdbc:postgresql://localhost:5432/postgres";
		String dbusername = "postgres";
		String dbpassword = "root";
		
		//get username and password from user
		String username = user.getUsername();
		String password = user.getPassword();
		
		//create sql statement
		String sql = "SELECT * FROM clc.user WHERE username = '" + username + "' and password = '" + password + "';";
		
		
		try {
			//try to create connection
			conn = DriverManager.getConnection(url, dbusername, dbpassword);
			Statement stmt = conn.createStatement();
			
			//get result set from select statement
			ResultSet result = stmt.executeQuery(sql);
			
			//create new user
			User returned = new User(null, null, null, null);
			//while result set has a user, set the username and password to the new user
			while (result.next()){
			    String newusername = result.getString("username");
			    String pass = result.getString("password");
			 
			    returned.setUsername(newusername);
			    returned.setPassword(pass);
			    
			}
			//close result set and connection
			result.close();
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			//return user
			return returned;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * inserts user to database
	 * 
	 * @param user
	 * @return boolean
	 */
	@Override
	public boolean create(User user) {
		//create connection credentials
		Connection conn = null;
		String url = "jdbc:postgresql://localhost:5432/postgres";
		String dbusername = "postgres";
		String dbpassword = "root";
		
		//get user variables
		String username = user.getUsername();
		String password = user.getPassword();
		String firstname = user.getFirstName();
		String lastname = user.getLastName();
		
		//create insert sql statement
		String sql = "INSERT INTO clc.user(username, password, firstname, lastname) VALUES (" + "'" + username + "'" + "," + "'" + password + "'" + "," + "'" + firstname + "'" + "," + "'" + lastname + "'" + ");";
		
		
		try {
			// create connection
			conn = DriverManager.getConnection(url, dbusername, dbpassword);
			Statement stmt = conn.createStatement();
			//execute insert statement
			int success = stmt.executeUpdate(sql);
			
			//close connection
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			//check if successful insert return boolean.
			if (success > 0) {
				return true;
			} else {
				return false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findById(int id) {
		//set database access credentials
				Connection conn = null;
				String url = "jdbc:postgresql://localhost:5432/postgres";
				String dbusername = "postgres";
				String dbpassword = "root";
				
				//create sql string
				String sql = "SELECT * FROM clc.user WHERE id = '"+ id +"';";
				
				
				try {
					//try to create connection
					conn = DriverManager.getConnection(url, dbusername, dbpassword);
					Statement stmt = conn.createStatement();
					
					//execute sql statement and get the result set
					ResultSet result = stmt.executeQuery(sql);
					//create new user
					User u = new User();
					
					//while has next in result set, set user u's variables
					while (result.next()){
						u.setFirstName(result.getString("firstname"));
					    u.setLastName(result.getString("lastname"));
					    u.setUsername(result.getString("username"));
					    u.setPassword("password is private information");
					    
					}

					//return product.
					return u;
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
				finally {
					if (conn != null) {
						try {
							conn.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}
				return null;
	}

	@Override
	public boolean update(User t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(User t) {
		// TODO Auto-generated method stub
		return false;
	}
}
