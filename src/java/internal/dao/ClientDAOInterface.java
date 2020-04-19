package internal.dao;

import internal.entity.Client;
import internal.exception.DAOException;
import java.util.List;

/**
 * Interface for EJB usage.
 * @author TheProthean
 */
public interface ClientDAOInterface {
	
	public List<Client> readClients() throws DAOException;
	
	public Client readClientById(Long id) throws DAOException;
        
        public Client readClientByName(String name) throws DAOException;
	
	public void insertClient(Client client) throws DAOException;
	
	public void deleteClient(Client client) throws DAOException;
	
}
