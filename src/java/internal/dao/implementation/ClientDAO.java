package internal.dao.implementation;

import java.util.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import internal.dao.ClientDAOInterface;
import internal.entity.Client;
import internal.entity.Client_;
import internal.exception.DAOException;

/**
 * DAO class for clients.
 * @author TheProthean
 *
 */
@Stateless
public class ClientDAO implements ClientDAOInterface {
	
    private static Logger logger = LogManager.getLogger();

    @PersistenceContext(unitName = "web_lab_1")
    private EntityManager entityManager;

    /**
     * constructor
     *
     */
    public ClientDAO() {  
    }
    
    /**
     * constructor
     * @param emf entityManager factory
     */
    public ClientDAO(EntityManager em) {
    	this.entityManager = em;
    }

    /**
     * read clients
     *
     * @return list of clients
     * @throws DAOException if Can't execute query or problems with connection
     */
    public List<Client> readClients() throws DAOException {
    	logger.info("readClients.Start");
        List<Client> clients = null;
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Client> query = cb.createQuery(Client.class);
            Root<Client> accountRoot = query.from(Client.class);
            clients = (List<Client>) entityManager.createQuery(query).getResultList();
        } catch (Exception e) {
            throw new DAOException("Read clients exception ", e);
        }
        return clients;
    }

    /**
     * read client by id
     *
     * @return client
     * @throws DAOException if Can't execute query or problems with connection
     */
    public Client readClientById(Long id) throws DAOException {
    	logger.info("readClientsById.Start");
        Client client = null;
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Client> query = cb.createQuery(Client.class);
            Root<Client> accountRoot = query.from(Client.class);
            query.where(cb.equal(accountRoot.get(Client_.id), id));
            client = entityManager.createQuery(query).getSingleResult();
        } catch (Exception e) {
            throw new DAOException("Read client exception ", e);
        } 
        return client;
    }
    
    /**
     * read client by id
     *
     * @return client
     * @throws DAOException if Can't execute query or problems with connection
     */
    public Client readClientByName(String name) throws DAOException {
    	logger.info("readClientsById.Start");
        Client client = null;
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Client> query = cb.createQuery(Client.class);
            Root<Client> accountRoot = query.from(Client.class);
            query.where(cb.equal(accountRoot.get(Client_.name), name));
            client = entityManager.createQuery(query).getSingleResult();
        } catch (Exception e) {
            throw new DAOException("Read client exception ", e);
        } 
        return client;
    }

    /**
     * insert client
     *
     * @throws DAOException if Can't execute query or problems with connection
     */
    public void insertClient(Client client) throws DAOException {
    	logger.info("insertClient.Start");
        try {
            entityManager.persist(client);
        } catch (Exception e) {
            throw new DAOException("Insert client exception ", e);
        } 
    }

    /**
     * delete client
     *
     * @throws DAOException if Can't execute query or problems with connection
     */
    public void deleteClient(Client client) throws DAOException {
    	logger.info("deleteClient.Start");
        try {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaDelete<Client> query = cb.createCriteriaDelete(Client.class);
            Root<Client> accountRoot = query.from(Client.class);
            query.where(cb.equal(accountRoot.get(Client_.id), client.getId()));
            entityManager.createQuery(query).executeUpdate();
        } catch (Exception e) {
            throw new DAOException("Delete client exception ", e);
        }
    }
    
}