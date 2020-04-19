package internal.dao.implementation;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import internal.dao.OrderDAOInterface;
import internal.entity.Client;
import internal.entity.MenuItem;
import internal.entity.Order_;
import internal.entity.Order;
import internal.entity.OrderItem;
import internal.exception.DAOException;

/**
 * DAO class for orders.
 * @author TheProthean
 *
 */
@Stateless
public class OrderDAO implements OrderDAOInterface {
	
    private static Logger logger = LogManager.getLogger();

    @PersistenceContext(unitName = "web_lab_1")
    private EntityManager entityManager;

    /**
     * constructor
     *
     */
    public OrderDAO() {  
    }
    
    /**
     * constructor
     * @param emf entityManager factory
     */
    public OrderDAO(EntityManager em) {
    	this.entityManager = em;
    }

    /**
     * read overdue orders
     *
     * @return list of orders
     * @throws DAOException if Can't execute query or problems with connection
     */
    public List<Order> readOverdueOrders() throws DAOException {
    	logger.info("readOverdueOrders.Start");
    	List<Order> orders = null;
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Order> query = cb.createQuery(Order.class);
            Root<Order> accountRoot = query.from(Order.class);
            query.where(cb.equal(accountRoot.get(Order_.paid), false));
            
            ParameterExpression<Date> parameter = cb.parameter(Date.class);
            Predicate endDatePredicate = cb.lessThanOrEqualTo(accountRoot.get(Order_.deliveryDeadline).as(java.sql.Date.class), parameter);
            query.where(endDatePredicate);
            
            orders = (List<Order>) entityManager.createQuery(query).setParameter(parameter, new Date()).getResultList();
        } catch (Exception e) {
            throw new DAOException("Read overdue orders exception ", e);
        }
        return orders;
    }

    /**
     * read orders by client id
     *
     * @return orders
     * @throws DAOException if Can't execute query or problems with connection
     */
    public List<Order> readOrdersByClientId(Long clientId) throws DAOException {
    	logger.info("readOrdersByClientId.Start");
    	List<Order> orders = null;
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Order> query = cb.createQuery(Order.class);
            Root<Order> accountRoot = query.from(Order.class);
            Client clientRef = entityManager.getReference(Client.class, clientId);
            System.out.println(clientRef.getName());
            Predicate pred = cb.equal(accountRoot.get(Order_.client), clientRef);
            query.where(pred);
            orders = (List<Order>) entityManager.createQuery(query).getResultList();
        } catch (Exception e) {
            throw new DAOException("Read orders by client id exception ", e);
        }
        return orders;
    }
    
    /**
     * read orders due to date
     *
     * @return orders
     * @throws DAOException if Can't execute query or problems with connection
     */
    public List<Order> readOrdersDueToDate(Date dueDate) throws DAOException {
    	logger.info("readOrdersDueToDate.Start");
    	List<Order> orders = null;
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Order> query = cb.createQuery(Order.class);
            Root<Order> accountRoot = query.from(Order.class);
            query.where(cb.equal(accountRoot.get(Order_.paid), false));
            
            ParameterExpression<Date> parameter = cb.parameter(Date.class);
            Predicate endDatePredicate = cb.lessThanOrEqualTo(accountRoot.get(Order_.deliveryDeadline).as(java.sql.Date.class), parameter);
            query.where(endDatePredicate);
            
            orders = (List<Order>) entityManager.createQuery(query).setParameter(parameter, dueDate).getResultList();
        } catch (Exception e) {
            throw new DAOException("Read orders due to date exception ", e);
        }
        return orders;
    }


    /**
     * insert order
     *
     * @throws DAOException if Can't execute query or problems with connection
     */
    public void insertOrder(Order order) throws DAOException {
    	logger.info("insertOrder.Start");
        try {
            Client client = entityManager.getReference(Client.class, order.getClient().getId());
            order.setClient(client);
            entityManager.persist(order);
        } catch (Exception e) {
            throw new DAOException("Insert order exception ", e);
        }
    }
    
    /**
     * change order state
     *
     * @throws DAOException if Can't execute query or problems with connection
     */
    public void changeOrderState(Order order) throws DAOException {
    	logger.info("changeOrderState.Start");
        try {
            order.setPaid(true);
            entityManager.merge(order);
        } catch (Exception e) {
            throw new DAOException("Change order state exception ", e);
        }
    }
}
