//Jacob Hushaw, Harold Berudez
//CST - 235, Jevon Jackson
//This is our own work.
package controllers;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import beans.Product;
import businessServices.ProductBusinessService;

@ManagedBean
@ViewScoped
public class ProductController {

	@EJB
	ProductBusinessService pbs;
	
	/**
	 * gets product from view creates product, sends it to business service
	 * returns view with success or fail message.
	 * 
	 * @param product
	 * @return view with message
	 */
	public String createProduct(Product product){
		
		//get product from view
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("product", product);		
		
		//create product
		Product newp = new Product(0,product.getName(),product.getDescription(),product.getPrice(), product.getQuantity());
		
		//call createProduct 
		boolean success = pbs.createProduct(newp);
		
		//set success or failed message
		String message = "";
		if (!success) {
			message = "failed to create product";
		} else {
			message = "successfully created product";
		}

		//add message
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));


	    //return view
				return "CreateProduct.xhtml";

			    
	}
	
	public String deleteProduct(Product product) {
		//get product from view
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("product", product);		
		
		//create product
		Product newp = new Product(product.getId(),product.getName(),product.getDescription(),product.getPrice(), product.getQuantity());
		
		//call deleteProduct
		boolean success = pbs.deleteProduct(newp);
		
		//set success or failed message
		String message = "";
		if (!success) {
			message = "failed to Delete product";
		} else {
			message = "successfully Deleted product";
		}

		//add message
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));


	    //return view
				return "ViewAllProducts.xhtml";
	}
	
	public String viewUpdateProduct(Product product) {
		//Pass Product to view
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("oldp", product);		

	    //return view
				return "UpdateProduct.xhtml";
	}
	
	public String updateProduct(Product product){		
		//create product
		Product newp = new Product(product.getId(),product.getName(),product.getDescription(),product.getPrice(), product.getQuantity());
		
		//call updateProduct 
		boolean success = pbs.updateProduct(newp);
		
		//set success or failed message
		String message = "";
		if (!success) {
			message = "failed to update product";
		} else {
			message = "successfully updated product";
		}

//		//add message
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));

	    //return view
				return "ViewAllProducts.xhtml";	    
	}
	
	public String ViewProduct(Product product) {
		//sends product to the next page
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("product", product);		
		
		//return view product
		return "ViewProduct.xhtml";
	}
	
	/**
	 * getter for pbs
	 * 
	 * @return productBusinessService
	 */
	public ProductBusinessService getService() {
		return pbs;
	}
	
}
