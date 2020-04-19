package internal.entity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * Metamodel for MenuItem.
 * @author TheProthean
 *
 */
@StaticMetamodel(MenuItem.class)
public class MenuItem_ {
	public static volatile SingularAttribute< MenuItem, String> name;
    public static volatile SingularAttribute< MenuItem, Double> cost;
    public static volatile SingularAttribute< MenuItem, Long> id;
}
