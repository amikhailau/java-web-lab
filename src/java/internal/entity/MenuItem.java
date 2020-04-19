package internal.entity;

import java.io.Serializable;

import javax.persistence.*;
/**
 * Database class for menu item.
 * @author TheProthean
 *
 */
@NamedQueries({
	@NamedQuery(
		name="readMenu",	
		query="select m from MenuItem m"
	),
	@NamedQuery(
		name="readMenuById",
		query="select m from MenuItem m where m.id = :id"
	),
	@NamedQuery(
		name="deleteMenuById",
		query="delete from MenuItem m where m.id = :id"
	)
})
@Entity(name="MenuItem")
@Table(name="menu")
public class MenuItem implements Serializable {
	@Id
	private Long id;
	private String name;
	private double cost;
	
	public MenuItem(Long id, String name, double cost) {
		this.id = id;
		this.name = name;
		this.cost = cost;
	}
	
	public MenuItem() {
		
	}
	
	public MenuItem(Long id) {
		this.id = id;
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
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
}
