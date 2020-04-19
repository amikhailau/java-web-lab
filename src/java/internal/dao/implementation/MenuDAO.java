package internal.dao.implementation;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import internal.dao.MenuDAOInterface;
import internal.entity.MenuItem;
import internal.entity.MenuItem_;
import internal.exception.DAOException;

/**
 * DAO class for menu.
 * @author TheProthean
 *
 */
@Stateless
public class MenuDAO implements MenuDAOInterface {
	
	private static Logger logger = LogManager.getLogger();

	@PersistenceContext(unitName = "web_lab_1")
    private EntityManager entityManager;

    /**
     * constructor
     *
     */
    public MenuDAO() {  
    }
    
    /**
     * constructor
     * @param emf entityManager factory
     */
    public MenuDAO(EntityManager em) {
    	this.entityManager = em;
    }

    /**
     * read all items on menu
     *
     * @return list of MenuItem
     * @throws DAOException if Can't execute query or problems with connection
     */
    public List<MenuItem> readMenu() throws DAOException {
    	logger.info("readMenu.Start");
        List<MenuItem> menu = null;
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<MenuItem> query = cb.createQuery(MenuItem.class);
            Root<MenuItem> accountRoot = query.from(MenuItem.class);
            menu = (List<MenuItem>) entityManager.createQuery(query).getResultList();
        } catch (Exception e) {
            throw new DAOException("Read menu exception ", e);
        }
        return menu;
    }

    /**
     * read menu item by id
     *
     * @return MenuItem
     * @throws DAOException if Can't execute query or problems with connection
     */
    public MenuItem readMenuItemById(Long id) throws DAOException {
    	logger.info("readMenuById.Start");
        MenuItem item = null;
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<MenuItem> query = cb.createQuery(MenuItem.class);
            Root<MenuItem> accountRoot = query.from(MenuItem.class);
            query.where(cb.equal(accountRoot.get(MenuItem_.id), id));
            item = entityManager.createQuery(query).getSingleResult();
        } catch (Exception e) {
            throw new DAOException("Read menu item exception ", e);
        }
        return item;
    }

    /**
     * insert menu item
     *
     * @throws DAOException if Can't execute query or problems with connection
     */
    public void insertMenuItem(MenuItem item) throws DAOException {
    	logger.info("insertMenuItem.Start");
        try {
            entityManager.persist(item);
        } catch (Exception e) {
            throw new DAOException("Insert menu item exception ", e);
        }
    }

    /**
     * delete menu item
     *
     * @throws DAOException if Can't execute query or problems with connection
     */
    public void deleteMenuItem(MenuItem item) throws DAOException {
    	logger.info("deleteMenuItem.Start");
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaDelete<MenuItem> query = cb.createCriteriaDelete(MenuItem.class);
            Root<MenuItem> accountRoot = query.from(MenuItem.class);
            query.where(cb.equal(accountRoot.get(MenuItem_.id), item.getId()));
            entityManager.createQuery(query).executeUpdate();
        } catch (Exception e) {
            throw new DAOException("Delete menu item exception ", e);
        }
    }
}
