package com.hc.entity;

import com.hc.bean.Dept;
import com.hc.bean.Emp;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.jdbc.Work;
import org.junit.Test;
import utils.HibernateUtil;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Properties;

/**
 * Created by Administrator on 2016/12/22.
 */
public class UserTest {
    public static void main(String[] args) {
        Configuration cfg=new Configuration().configure();
        Properties properties=cfg.getProperties();

        StandardServiceRegistry serviceRegistry=new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory=new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.getTransaction();
//        try{
//            tx.begin();
//
//            User user = new User();
//            user.setId(8);
//            user.setName("王五");
//            user.setPassword("9898");
//            session.save(user);
//
//            Dept dept=new Dept();
//            dept.setDname("研发部");
//            dept.setLoc("海底");
//            session.save(dept);
//            tx.commit();
//        }catch (Exception e){
//            tx.rollback();
//            e.printStackTrace();
//        }finally {
//            session.close();
//            tx=null;
            StandardServiceRegistryBuilder.destroy(serviceRegistry);
  //      }


    }

    @Test
    public void testSession(){
        Session session = HibernateUtil.getCurrentSession();
        Transaction transaction = session.getTransaction();
        try{
            transaction.begin();
            Dept dept=new Dept();
            dept.setLoc("aa");
             dept.setDname("bb");
            session.save(dept);
//            Emp emp=new Emp(3,"empp","2011-10-10",22);
//            session.save(emp);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
            transaction=null;
        }
    }

    @Test
    public void fun3() {
        Session session = HibernateUtil.openSession();
        Transaction tx = session.getTransaction();

        try {
            tx.begin();
            //get查找，load查找具有懒加载特性只有使用到在查询
            Emp emp = session.get(Emp.class, 1);
            System.out.println(emp);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            tx = null;
        }
    }

    @Test
    public void testLoad(){
        Session session = HibernateUtil.openSession();
        Transaction tx = session.getTransaction();

        try {
            tx.begin();
            //get查找，load查找具有懒加载特性只有使用到在查询
            Emp emp = session.load(Emp.class, 1);
            System.out.println(emp);
//            System.out.println(emp.getDept());
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            tx = null;
        }
    }
    @Test
    public void testUpdate(){
        Session session = HibernateUtil.openSession();
        Transaction tx = session.getTransaction();

        try {
            tx.begin();
            //get查找，load查找具有懒加载特性只有使用到在查询
//           session.update(new Dept(2,"vv","天堂"));
            System.out.println();
//            System.out.println(emp.getDept());
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
            tx = null;
        }
    }
    @Test
    public void testOneTomany(){
        Session session = HibernateUtil.getCurrentSession();
        Transaction transaction = session.getTransaction();
        try{
            transaction.begin();
//            Dept dept=new Dept(1,"aa","aa");
//            session.save(dept);
//            Emp emp1=new Emp(5,"empp","2011-10-10",22);
//            Emp emp2=new Emp(6,"empp44","2011-10-10",22);
//            dept.getEmps().add(emp1);
//            dept.getEmps().add(emp2);
//            session.save(emp1);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
            transaction=null;
        }

    }
    @Test
    public void testStatus(){
        Session session = HibernateUtil.getCurrentSession();
        Transaction transaction = session.getTransaction();
        try{
            transaction.begin();
           User2 user2=new User2();
            user2.setName("aa");
            user2.setPass("BB");
            System.out.println(user2);
            session.save(user2);
            System.out.println(user2);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
            transaction=null;
        }
    }

    /**
     * 对于持久化的对象修改其对象的能容时不需要调用session.update方法
     * 因为使用的为同一个session
     * session中不能存在两个对象OID
     */
    @Test
    public void testGetUpdate(){
        Session session = HibernateUtil.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        User2 user2= session.get(User2.class,1);

        user2.setName("ss");
        transaction.commit();
        session.close();
    }

    /**
     * 当session关闭时update数据时需要手动在开启一个session
     */
    @Test
    public void testGetUpdate2(){
        Session session = HibernateUtil.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        User2 user2= session.get(User2.class,1);
        System.out.println(user2);
        transaction.commit();
        session.close();
         session= HibernateUtil.openSession();
        Transaction tx= session.getTransaction();
         tx.begin();
        user2.setName("ss");
        session.update(user2);
        tx.commit();

    }

    /**
     * saveOrUpdate如果oid不存在会报异常
     * 存在则更新
     */
    @Test
    public void testSaveOrUpdate(){
        Session session = HibernateUtil.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        User2 user2=new User2();
        user2.setId(4);
        user2.setName("ddf火人");
        user2.setPass("ddf");
        session.saveOrUpdate(user2);
        transaction.commit();
        session.close();

    }

    /**
     * evict为将session中保存的持久化对象给移除变成游离对象
     * 当在此调用session。update的方法时会执行更新操作
     */
    @Test
    public void testEvict(){
        Session session = HibernateUtil.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
            User2 user1=session.get(User2.class,4);
        user1.setName("llllll");
            User2 user2=session.get(User2.class,5);
        session.evict(user1);
        session.update(user1);

        transaction.commit();
        session.close();

    }
    @Test
    public void testHibernateProcedure(){
        Session session = HibernateUtil.openSession();
        session.doWork(new Work() {
            public void execute(Connection connection) throws SQLException {
                CallableStatement stmt=connection.prepareCall("{call proc(?)}");
                stmt.registerOutParameter(1, Types.INTEGER);
                stmt.execute();
                int i=stmt.getInt(1);
                System.out.println("查询结果：="+i);


            }
        });

    }

}