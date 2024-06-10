//Jacob Hushaw, Harold Berudez
//CST - 235, Jevon Jackson
//This is our own work.
package dataAccessServices;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;

import beans.Product;
import dataAccessServices.DataAccessInterface;

@Stateless
@Local(DataAccessInterface.class)
@LocalBean
@ManagedBean
public class ProductDataAccess implements DataAccessInterface<Product> {

	/**
	 * finds all products from database
	 * 
	 * @return List<Product>
	 */
	@Override
	public List<Product> findAll() {
		//set database access credentials
		Connection conn = null;
		String url = "jdbc:postgresql://localhost:5432/postgres";
		String dbusername = "postgres";
		String dbpassword = "root";
		
		//create sql string
		String sql = "SELECT * FROM clc.product;";
		
		
		try {
			//try to create connection
			conn = DriverManager.getConnection(url, dbusername, dbpassword);
			Statement stmt = conn.createStatement();
			
			//execute sql statement and get the result set
			ResultSet result = stmt.executeQuery(sql);
			//create new list of products
			List<Product> products = new ArrayList<Product> ();
			
			//while has next in result set, create new product add it to list
			while (result.next()){
				int id = result.getInt("id");
			    String name = result.getString("name");
			    String description = result.getString("description");
			    float price = result.getFloat("price");
			    int quantity = result.getInt("quantity");
			    
			    Product p = new Product(id, name, description, price, quantity);
			    
			    products.add(p);
			    
			}

			//return list.
			return products;
			
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
	
	/**
	 * insert product to database
	 * 
	 * @param product
	 * @return boolean
	 */
	@Override
	public boolean create(Product product) {
		//set database access credentials
		Connection conn = null;
		String url = "jdbc:postgresql://localhost:5432/postgres";
		String dbusername = "postgres";
		String dbpassword = "root";
		
		//get all variables from product
		String name = product.getName();
		String description = product.getDescription();
		float price = product.getPrice();
		int quantity = product.getQuantity(); 
		
		//create sql statement
		String sql = "INSERT INTO clc.product(name, description, price, quantity) VALUES (" + "'" + name + "'" + "," + "'" + description + "'" + "," + "'" + price + "'" + "," + "'" + quantity + "'" + ");";
		
		
		try {
			//try to create connection
			conn = DriverManager.getConnection(url, dbusername, dbpassword);
			Statement stmt = conn.createStatement();
			//execute insert, get int for success
			int success = stmt.executeUpdate(sql);
			
			conn.close();
			
			//check if inserted return boolean
			if (success > 0) {
				return true;
			} else {
				return false;
			}
			
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
		return false;
	}

	@Override
	public Product findById(int id) {
		//set database access credentials
		Connection conn = null;
		String url = "jdbc:postgresql://localhost:5432/postgres";
		String dbusername = "postgres";
		String dbpassword = "root";
		
		//create sql string
		String sql = "SELECT * FROM clc.product WHERE id = '"+ id +"';";
		
		
		try {
			//try to create connection
			conn = DriverManager.getConnection(url, dbusername, dbpassword);
			Statement stmt = conn.createStatement();
			
			//execute sql statement and get the result set
			ResultSet result = stmt.executeQuery(sql);
			//create new product
			Product p = new Product();
			
			//while has next in result set, set product p's variables
			while (result.next()){
				p.setId(id);
			    p.setName(result.getString("name"));
			    p.setDescription(result.getString("description"));
			    p.setPrice(result.getFloat("price"));
			    p.setQuantity(result.getInt("quantity"));
			    
			}

			//return product.
			return p;
			
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
	public boolean update(Product product) {
		//set database access credentials
		Connection conn = null;
		String url = "jdbc:postgresql://localhost:5432/postgres";
		String dbusername = "postgres";
		String dbpassword = "root";
		
		//get id from product
		String name = product.getName();
		String description = product.getDescription();
		float price = product.getPrice();
		int quantity = product.getQuantity(); 
		int id = product.getId();
		
		System.out.println("product stuff" + name + description + price + quantity+ id);
		
		//create sql statement
		String sql = "UPDATE clc.product SET name='"+name+"', description='"+description+"', price='"+price+"', quantity='"+quantity+"' WHERE id = '"+id+"';";
		
		System.out.println("UPDATE clc.product SET name='"+name+"', description='"+description+"', price='"+price+"', quantity='"+quantity+"' WHERE id = '"+id+"';");
		try {
			//try to create connection
			conn = DriverManager.getConnection(url, dbusername, dbpassword);
			Statement stmt = conn.createStatement();
			//execute update, get int for success
			int success = stmt.executeUpdate(sql);
			System.out.println(success);
			conn.close();
			
			//check if update return boolean
			if (success > 0) {
				return true;
			} else {
				return false;
			}
			
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
		return false;
	}
	

	@Override
	public boolean delete(Product product) {
		//set database access credentials
		Connection conn = null;
		String url = "jdbc:postgresql://localhost:5432/postgres";
		String dbusername = "postgres";
		String dbpassword = "root";
		
		//get id from product
		
		int id = product.getId();
		
		//create sql statement
		String sql = "DELETE FROM clc.product WHERE id = "+id+";";
		
		
		try {
			//try to create connection
			conn = DriverManager.getConnection(url, dbusername, dbpassword);
			Statement stmt = conn.createStatement();
			//execute delete, get int for success
			int success = stmt.executeUpdate(sql);
			
			conn.close();
			
			//check if deleted return boolean
			if (success > 0) {
				return true;
			} else {
				return false;
			}
			
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
		return false;
	}
}
