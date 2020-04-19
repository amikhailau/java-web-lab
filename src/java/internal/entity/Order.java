package internal.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;

/**
 * Database class for order.
 * @author TheProthean
 *
 */
@NamedQueries({
	@NamedQuery(
			name="readOverdueOrders",
			query="select co from Order co where co.deliveryDeadline < 'now' and co.paid = 'f'"
	),
	@NamedQuery(
			name="readOrdersByClientId",
			query="select co from Order co where co.client.id = :client_id"
	),
	@NamedQuery(
			name="readOrdersDueToDate",
			query="select co from Order co where co.deliveryDeadline <= :due_date and co.paid = 'f'"
	)
})
@Entity(name="Order")
@Table(name="client_order")
public class Order implements Serializable {
	@Id
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "client_id")
	private Client client;
	
        @Column(name="total_cost")
	private double orderSum;
	
	@Column(name="delivery_deadline")
	private Date deliveryDeadline;
	private boolean paid;
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public double getOrderSum() {
		return orderSum;
	}
	public void setOrderSum(double orderSum) {
		this.orderSum = orderSum;
	}
	public Date getDeliveryDeadline() {
		return deliveryDeadline;
	}
	public void setDeliveryDeadline(Date deliveryDeadline) {
		this.deliveryDeadline = deliveryDeadline;
	}
	public boolean isPaid() {
		return paid;
	}
	public void setPaid(boolean paid) {
		this.paid = paid;
	}
	public Order(Long id, Client client, double orderSum, Date deliveryDeadline, boolean paid) {
		this.id = id;
		this.client = client;
		this.orderSum = orderSum;
		this.deliveryDeadline = deliveryDeadline;
		this.paid = paid;
	}
	public Order() {
		
	}
	@Override
	public String toString() {
		String description = "Order id= " + id + ",\n client= " + client.getName() + ",\n orderSum=";
		description += orderSum + " deliveryDeadline= "
				+ deliveryDeadline + ",\n paid= " + paid + "\n";
		return description;
	}
	
}
