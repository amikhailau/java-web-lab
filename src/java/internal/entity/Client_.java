package internal.entity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * Metamodel for client class.
 * @author TheProthean
 *
 */
@StaticMetamodel(Client.class)
public class Client_ {
	public static volatile SingularAttribute< Client, String> name;
    public static volatile SingularAttribute< Client, String> phone;
    public static volatile SingularAttribute< Client, Long> id;
}
