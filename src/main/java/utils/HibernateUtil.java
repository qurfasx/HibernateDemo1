package utils;

import com.hc.bean.Emp;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2016/12/22.
 */
public class HibernateUtil {
    private static SessionFactory factory=null;
    static {
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
        factory=new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();


    }

    public static Session getCurrentSession(){
        return  factory.getCurrentSession();
    }
    public static  Session openSession(){
        return factory.openSession();
    }

    public static void closeSession(Session session){
        if (session!=null){
            session.close();
        }
    }
    //////////////////////////////////////////////////////////////////////////

}
