package internal.dao;

import internal.entity.User;
import internal.exception.DAOException;

/**
 * Interface for EJB usage.
 * @author TheProthean
 */
public interface UsersDAOInterface {
	
	public User readUserByName(String name) throws DAOException;
	
	public void insertUser(User user) throws DAOException;
	
}
