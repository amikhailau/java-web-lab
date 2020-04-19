package internal.entity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * Metamodel for order item class.
 * @author TheProthean
 *
 */
@StaticMetamodel(OrderItem.class)
public class OrderItem_ {
	public static volatile SingularAttribute< OrderItem, Order> order;
	public static volatile SingularAttribute< OrderItem, Integer> amount;
    public static volatile SingularAttribute< OrderItem, MenuItem> menuItem;
    public static volatile SingularAttribute< OrderItem, Long> id;
}
