package internal.entity;

import java.util.Date;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * Metamodel for order class.
 * @author TheProthean
 *
 */
@StaticMetamodel(Order.class)
public class Order_ {
    public static volatile SingularAttribute< Order, Double> orderSum;
    public static volatile SingularAttribute< Order, Client> client;
    public static volatile SingularAttribute< Order, Date> deliveryDeadline;
    public static volatile SingularAttribute< Order, Boolean> paid;
    public static volatile SingularAttribute< Order, Long> id;
}
