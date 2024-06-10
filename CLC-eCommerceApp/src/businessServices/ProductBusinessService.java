//Jacob Hushaw, Harold Berudez
//CST - 235, Jevon Jackson
//This is our own work.
package businessServices;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;

import beans.Product;
import dataAccessServices.ProductDataAccess;

@Stateless
@ManagedBean
public class ProductBusinessService {

	@EJB
	ProductDataAccess pda;
	//List of all products retrieved from the databse
	public static List<Product> products = new ArrayList<Product> ();
   
	
	public ProductBusinessService() {

	}
    
	/**
	 * sends a Product down to the data access layer to be created.
	 * 
	 * @param product
	 * @return boolean
	 */
	public boolean createProduct(Product product) {
		//send product to data access layer return boolean if inserted
		boolean returned = pda.create(product);
		return returned;
	}
	
	public boolean deleteProduct(Product product) {
		//send product to data access layer return boolean if deleted
		boolean returned = pda.delete(product);
		return returned;
	}
	
	public boolean updateProduct(Product product) {
		//send product to data access layer return boolean if updated
				boolean returned = pda.update(product);
				return returned;
	}
	
	public Product findById(int id) {
		Product p = pda.findById(id);
		if (p.getName() == null || p == null) {
			p.setName("this product does not exist");
			return p;
		} else {
			return p;
		}
	}
	
	/**
	 * getter for product list
	 * 
	 * @return List<Product>
	 */
	public List<Product> getProducts(){
		//gets all products
		return pda.findAll();
	}
	
	/**
	 * sets product list.
	 * 
	 * @param product
	 */
	public void setProducts(List<Product> product) {
		products = product;
	}


    
}
