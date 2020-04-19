package internal.dao;

import javax.ejb.Remote;
import java.util.List;
import java.util.Date;

import internal.entity.Order;
import internal.exception.DAOException;

/**
 * Interface for EJB usage.
 * @author TheProthean
 */
@Remote
public interface OrderDAOInterface {
	
	public List<Order> readOverdueOrders() throws DAOException;
	
	public List<Order> readOrdersByClientId(Long clientId) throws DAOException;
	
	public List<Order> readOrdersDueToDate(Date dueDate) throws DAOException;
	
	public void insertOrder(Order order) throws DAOException;
	
	public void changeOrderState(Order order) throws DAOException;

}
