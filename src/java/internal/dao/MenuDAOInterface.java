package internal.dao;

import java.util.List;

import internal.entity.MenuItem;
import internal.exception.DAOException;

/**
 * Interface for EJB usage.
 * @author TheProthean
 */
public interface MenuDAOInterface {
	
	public List<MenuItem> readMenu() throws DAOException;
	
	public MenuItem readMenuItemById(Long id) throws DAOException;
	
	public void insertMenuItem(MenuItem item) throws DAOException;
	
	public void deleteMenuItem(MenuItem item) throws DAOException;

}
