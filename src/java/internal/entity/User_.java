package internal.entity;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import internal.entity.User;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * Metamodel for user class.
 * @author TheProthean
 *
 */
@StaticMetamodel(User.class)
public class User_ {
    public static volatile SingularAttribute< User, String> name;
    public static volatile SingularAttribute< User, String> password;
    public static volatile SingularAttribute< User, Long> id;
    public static volatile SingularAttribute< User, Integer> role;
}