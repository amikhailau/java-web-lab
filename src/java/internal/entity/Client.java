package internal.entity;

import java.io.Serializable;

import javax.persistence.*;
/**
 * Database class for client.
 * @author TheProthean
 *
 */
@NamedQueries({
	@NamedQuery(
		name="readClients",	
		query="select c from Client c"
	),
	@NamedQuery(
		name="readClientById",
		query="select c from Client c where c.id = :id"
	),
	@NamedQuery(
		name="deleteClientById",
		query="delete from Client c where c.id = :id"
	)
})
@Entity(name="Client")
@Table(name="client")
public class Client implements Serializable{
	@Id
	private Long id;
	private String name;
	private String phone;
	
	public Client(Long id, String name, String phone) {
		this.id = id;
		this.name = name;
		this.phone = phone;
	}
	
	public Client(Long id) {
		this.id = id;
	}
	
	public Client() {
		
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
