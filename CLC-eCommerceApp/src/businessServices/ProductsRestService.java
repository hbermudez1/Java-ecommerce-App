package businessServices;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import beans.Product;

@RequestScoped
@Path("/products")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class ProductsRestService {
	
	@EJB
	ProductBusinessService service = new ProductBusinessService();
	
	/**
	 * returns all products in json format
	 * 
	 * @return list<product>
	 */
	@GET
	@Path("/getallproductsjson")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getProductsAsJson(){
		return service.getProducts();
	}
	
	/**
	 * returns all products in xml format
	 * 
	 * @return list<product>
	 */
	@GET
	@Path("/getallproductsxml")
	@Produces(MediaType.APPLICATION_XML)
	public Product [] getProductsAsXml() {
		List<Product> products = service.getProducts();
		return products.toArray(new Product[products.size()]);
	}
	
	/**
	 * returns one products in json format
	 * 
	 * @param id
	 * @return product
	 */
	@GET
	@Path("/getproductjson/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Product getProductAsJson(@PathParam("id") int id){
		return service.findById(id);
	}
	
	/**
	 * returns one products in xml format
	 * 
	 * @param id
	 * @return product
	 */
	@GET
	@Path("/getproductxml/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public Product getProductAsXml(@PathParam("id") int id) {
		return service.findById(id);
	}

}
