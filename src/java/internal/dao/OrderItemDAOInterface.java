package internal.dao;

import java.util.List;

import internal.entity.OrderItem;
import internal.exception.DAOException;

/**
 * Interface for EJB usage.
 * @author TheProthean
 */
public interface OrderItemDAOInterface {
	
	public List<OrderItem> readOrderItems(Long orderId) throws DAOException;
	
	public void insertOrderItem(OrderItem item) throws DAOException;

}
