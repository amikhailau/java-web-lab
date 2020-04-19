package internal.dao;

import javax.ejb.Remote;

import internal.entity.User;
import internal.exception.DAOException;
import java.util.List;

/**
 * Interface for EJB usage.
 * @author TheProthean
 */
@Remote
public interface UsersDAOInterface {
	
	public User readUserByName(String name) throws DAOException;
	
	public void insertUser(User user) throws DAOException;
	
}
