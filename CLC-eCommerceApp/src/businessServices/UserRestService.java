package businessServices;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import beans.User;

@RequestScoped
@Path("/users")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class UserRestService {
	
	@EJB
	UserBusinessService service = new UserBusinessService();
	
	/**
	 * returns one user in json format
	 * 
	 * @param id
	 * @return user
	 */
	@GET
	@Path("/getuserjson/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getProductAsJson(@PathParam("id") int id){
		return service.findById(id);
	}
	
	/**
	 * returns one user in xml format
	 * 
	 * @param id
	 * @return user
	 */
	@GET
	@Path("/getuserxml/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public User getProductAsXml(@PathParam("id") int id) {
		return service.findById(id);
	}

}
