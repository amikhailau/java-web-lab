/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package internal.dao.implementation;

import internal.dao.UsersDAOInterface;
import internal.entity.User;
import internal.entity.User_;
import internal.exception.DAOException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * DAO class for clients.
 * @author TheProthean
 *
 */
@Stateless
public class UsersDAO implements UsersDAOInterface {
    
    private static Logger logger = LogManager.getLogger();

    @PersistenceContext(unitName = "web_lab_1")
    private EntityManager entityManager;
    
    /**
     * constructor
     *
     */
    public UsersDAO() {  
    }
    
    /**
     * constructor
     * @param emf entityManager factory
     */
    public UsersDAO(EntityManager em) {
    	this.entityManager = em;
    }
    
    /**
     * read user by name
     *
     * @return user
     * @throws DAOException if Can't execute query or problems with connection
     */
    public User readUserByName(String name) throws DAOException {
    	logger.info("readUserByName.Start");
        User user = null;
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<User> query = cb.createQuery(User.class);
            Root<User> accountRoot = query.from(User.class);
            query.where(cb.equal(accountRoot.get(User_.name), name));
            user = entityManager.createQuery(query).getSingleResult();
        } catch (Exception e) {
            throw new DAOException("Read user by name exception ", e);
        }
        return user;
    }
    
    /**
     * insert client
     *
     * @throws DAOException if Can't execute query or problems with connection
     */
    public void insertUser(User user) throws DAOException {
    	logger.info("insertClient.Start");
        try {
            entityManager.persist(user);
        } catch (Exception e) {
            throw new DAOException("Insert user exception ", e);
        } 
    }

    
}
