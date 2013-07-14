package server.database;

import java.util.List;

import server.ServerException;
import shared.model.Field;

/**
 * @author zaphinath
 *
 */
public class FieldDB {

	/**
	 * Class Constructor
	 * @param db
	 */
	public FieldDB(Database db) {
		
		
	}
	
	/**
	 * Queries the database for all fields and returns them in a list
	 * @return List of fields
	 * @throws ServerException
	 */
	public List<Field> getall() throws ServerException {
		return null;
	}
	
	/**
	 * Adds a field to the database
	 * @param field
	 * @throws ServerException
	 */
	public void add(Field field) throws ServerException {
		
	}
	
	/**
	 * Updates an existing field in the database
	 * @param field
	 * @throws ServerException
	 */
	public void update(Field field) throws ServerException {
		
	}
	
	/**
	 * Deletes a field from the database
	 * @param field
	 * @throws ServerException
	 */
	public void delete(Field field) throws ServerException {
		
	}
}
