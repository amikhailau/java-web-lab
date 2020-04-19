package internal.dao;

import javax.ejb.Remote;
import java.util.List;

import internal.entity.OrderItem;
import internal.exception.DAOException;

/**
 * Interface for EJB usage.
 * @author TheProthean
 */
@Remote
public interface OrderItemDAOInterface {
	
	public List<OrderItem> readOrderItems(Long orderId) throws DAOException;
	
	public void insertOrderItem(OrderItem item) throws DAOException;

}
