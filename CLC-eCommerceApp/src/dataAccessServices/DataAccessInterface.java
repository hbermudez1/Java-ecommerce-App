//Jacob Hushaw, Harold Berudez
//CST - 235, Jevon Jackson
//This is our own work.
package dataAccessServices;

import java.util.List;

//Generic Interface including all CRUD methods necessary for any DAO
public interface DataAccessInterface<T> {
	
	public List<T> findAll();
	public T findById(int id);
	
	public boolean create(T t);
	public boolean update(T t);
	public boolean delete(T t);

}
