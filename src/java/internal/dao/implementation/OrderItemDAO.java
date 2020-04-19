package internal.dao.implementation;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import internal.entity.OrderItem_;
import internal.dao.OrderItemDAOInterface;
import internal.entity.Order;
import internal.entity.OrderItem;
import internal.exception.DAOException;

/**
 * DAO Class for order items;
 * @author TheProthean
 *
 */
@Stateless
public class OrderItemDAO implements OrderItemDAOInterface {
	
	private static Logger logger = LogManager.getLogger();
	
	@PersistenceContext(unitName = "web_lab_1")
    private EntityManager entityManager;

    /**
     * constructor
     *
     */
    public OrderItemDAO() {  
    }
    
    /**
     * constructor
     * @param emf entityManager factory
     */
    public OrderItemDAO(EntityManager em) {
    	this.entityManager = em;
    }

    /**
     * read order items for order
     *
     * @return list of order items
     * @throws DAOException if Can't execute query or problems with connection
     */
    public List<OrderItem> readOrderItems(Long orderId) throws DAOException {
    	logger.info("readOrderItems.Start");
        List<OrderItem> items = null;
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<OrderItem> query = cb.createQuery(OrderItem.class);
            Root<OrderItem> accountRoot = query.from(OrderItem.class);
            Order orderRef = entityManager.getReference(Order.class, orderId);
            query.where(cb.equal(accountRoot.get(OrderItem_.order), orderRef));
            items = (List<OrderItem>) entityManager.createQuery(query).getResultList();
        } catch (Exception e) {
            throw new DAOException("Read order items exception ", e);
        }
        return items;
    }

    /**
     * insert order item
     *
     * @throws DAOException if Can't execute query or problems with connection
     */
    public void insertOrderItem(OrderItem item) throws DAOException {
    	logger.info("insertOrderItem.Start");
        try {
            entityManager.persist(item);
        } catch (Exception e) {
            throw new DAOException("Read order items exception ", e);
        }
    }

}
