package internal.entity;

import java.io.Serializable;

import javax.persistence.*;
/**
 * Database class for order item.
 * @author TheProthean
 *
 */
@NamedQueries({
	@NamedQuery(
			name="readOrderItems",
			query="select mo from OrderItem mo where mo.order.id=:order_id"
	)
})
@Entity(name="OrderItem")
@Table(name="menu_order")
public class OrderItem implements Serializable {
	@Id
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "menu_id")
	private MenuItem menuItem;

	@ManyToOne(fetch = FetchType.EAGER, cascade= {CascadeType.ALL})
    @JoinColumn(name = "order_id")
	private Order order;
	
	private int amount;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public MenuItem getMenuItem() {
		return menuItem;
	}
	public void setMenuItem(MenuItem menuItem) {
		this.menuItem = menuItem;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public OrderItem(Long id, MenuItem menu, Order order, int amount) {
		this.id = id;
		this.menuItem = menu;
		this.order = order;
		this.amount = amount;
	}
	public OrderItem() {
		
	}
	@Override
	public String toString() {
		return "{OrderItem id= " + id + ", menu= " + menuItem.getName() + ", orderId= " + order.getId() + ", amount= " + amount + "}";
	}
	
}
