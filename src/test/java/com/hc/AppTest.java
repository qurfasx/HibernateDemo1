package com.hc;

import com.hc.bean.Dept;
import com.hc.bean.Emp;

import junit.framework.Test;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest  {
    /**
     * hql的Hibernate 检索查询与sql查询相似
     */
    @org.junit.Test
    public void HibernateHQL(){
        Session session = HibernateUtil.getCurrentSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            String hql="from Emp";//hql查询面向对象的查询对象为实体类
            Query query = session.createQuery(hql);
            List<Emp> list = query.list();
            for (Emp emp:list
                 ) {
                System.out.println(emp);
            }
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }finally {
            transaction=null;
            session.close();
        }
    }
    @org.junit.Test
    public void testhqlOpen(){
        Session session = HibernateUtil.openSession();
        String hql="from Emp where sal>:sal and hiredate >:hiredate";//hql查询面向对象的查询对象为实体类
        Query query = session.createQuery(hql);
        query.setParameter("sal",1500f);
        SimpleDateFormat sdf=new SimpleDateFormat("yyy-mm-dd");
        try {
            Date date = sdf.parse("1982-10-10");
        query.setParameter("hiredate",date);
        List<Emp> list = query.list();
        for (Emp emp:list
                ) {
            System.out.println(emp);
        }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    @org.junit.Test
    public void testCondion(){
        Session session = HibernateUtil.openSession();
        String hql="from Emp where sal> ? and hiredate >?";
        Query query = session.createQuery(hql);
        query.setParameter(0,1500f);
//        SimpleDateFormat sdf=new SimpleDateFormat("yyy-mm-dd");
//        Date date = sdf.parse("1987-04-19");
  //      query.setParameter(1,date)
        List<Emp> list = query.list();
        for (Emp emp:list
                ) {
            System.out.println(emp);
        }
    }
    @org.junit.Test
    public void testCondion2(){
        Session session = HibernateUtil.openSession();
        String hql="from Emp";
        Query query = session.createQuery(hql);
        List<Emp> list = query.list();
        for (Emp emp:list
                ) {
            System.out.println(emp);
        }
    }
    @org.junit.Test
    public void testDno(){
        Session session = HibernateUtil.openSession();
        String hql="from Emp where dept =:abcd";
        Query query = session.createQuery(hql);
        Dept dept=new Dept();
        dept.setDeptno((byte)20);
        query.setParameter("abcd",dept);
        List<Emp> list = query.list();
        for (Emp emp:list
                ) {
            System.out.println(emp+" "+emp.getDept().getDeptno());
        }
    }
    @org.junit.Test
    public void testpagesize(){
        Session session = HibernateUtil.openSession();
        String hql="from Emp";
        Query query = session.createQuery(hql);
        int pageno=2;
        int pagesize=4;
        query.setFirstResult((pageno-1)*pagesize);
        query.setMaxResults(pagesize);
        List<Emp> list= query.list();
        for (Emp emp:list
                ) {
            System.out.println(emp);
        }
    }
    @org.junit.Test
    public void testAnno(){
        Session session = HibernateUtil.getCurrentSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            Query queryAll = session.getNamedQuery("queryAll");
            List<Emp> list = queryAll.list();
            for (Emp emp:list
                    ) {
                System.out.println(emp);
            }
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }finally {
            transaction=null;
            session.close();
        }
    }
    @org.junit.Test
    public void testFile(){
        Session session = HibernateUtil.getCurrentSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            String hql="select ename,job,sal from Emp";
            Query query = session.createQuery(hql);
            List<Object[]> list = query.list();
            for (Object[] emp:list
                    ) {
                System.out.println(emp[0]+" "+emp[1]+" "+emp[2]);
            }
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }finally {
            transaction=null;
            session.close();
        }
    }
    @org.junit.Test
    public void test11(){
        Session session = HibernateUtil.getCurrentSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            String hql="select new Emp(empno, ename, sal, dept )from Emp";
            Query query = session.createQuery(hql);
            List<Emp> list = query.list();
            for (Emp emp:list
                    ) {
                System.out.println(emp);
            }
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }finally {
            transaction=null;
            session.close();
        }
    }
    @org.junit.Test
    public void test12(){
        Session session = HibernateUtil.getCurrentSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            String hql="select distinct dept.deptno  from Emp";
            Query query = session.createQuery(hql);
            List<Integer> list = query.list();
            for (Integer deptno:list
                    ) {
                System.out.println(deptno);
            }
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }finally {
            transaction=null;
            session.close();
        }
    }

    //分页查询
    int pagesize=5;
    public void findByPage(int pageNum){
        Session session = HibernateUtil.getCurrentSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            String hql="from Emp ORDER BY sal desc ";
            Query query = session.createQuery(hql);
            List<Emp> list = query.setFirstResult((pageNum-1)*pagesize)
                                    .setMaxResults(pagesize)
                                    .list();
            for (Emp  emp:list
                    ) {
                System.out.println(emp);
            }

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }finally {
            session.close();
        }
    }

    @org.junit.Test
    public void test33(){
        findByPage(2);
    }

    @org.junit.Test
    public void  textLeftJoin(){
        Session session = HibernateUtil.getCurrentSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            String hql="select distinct dept from Dept dept left join fetch dept.emps";
            Query query = session.createQuery(hql);
            List<Dept> depts = query.list();
            for (Dept dept:depts
                 ) {
                System.out.println(dept);
            }

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }finally {
            session.close();
        }
    }
    @org.junit.Test
    public  void testleftjoin(){
        Session session = HibernateUtil.getCurrentSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            String hql=" from Dept dept left join dept.emps";
            Query query = session.createQuery(hql);
            List<Object[]> list = query.list();
            for (Object[] objects:list
                 ) {
                for (int i = 0; i <objects.length ; i++) {
                    System.out.print(objects[i]+"   ");
                    System.out.println();
                }
            }
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }finally {
            session.close();
        }
    }

    @org.junit.Test
        public void testleftjoin2(){
            Session session = HibernateUtil.getCurrentSession();
            Transaction transaction = session.getTransaction();
            try {
                transaction.begin();
                String hql="select distinct dept from Dept dept left join dept.emps";
                Query query = session.createQuery(hql);
                List<Dept> list = query.list();
                for (Dept dept:list
                        ) {
                    System.out.println(dept+"   "+dept.getEmps());
                }
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
            }finally {
                session.close();
            }
    }
    //多对一的内连接
    @org.junit.Test
    public void testManytoone(){
        Session session = HibernateUtil.getCurrentSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            String hql="from Emp  e inner join fetch  e.dept";
            Query query = session.createQuery(hql);
            List<Emp> list = query.list();
            for (Emp emp:list
                    ) {
                System.out.println(emp+"   "+emp.getDept().getDname());
            }
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }finally {
            session.close();
        }
    }
    @org.junit.Test
    public void testSingle(){
        Session session = HibernateUtil.getCurrentSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            String hql="from Dept where deptno=10";
            Query query = session.createQuery(hql);
            Dept dept = (Dept) query.uniqueResult();
            System.out.println(dept);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }finally {
            session.close();
        }
    }
    @org.junit.Test
    public void testQBC(){
        Session session = HibernateUtil.getCurrentSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            Criteria criteria = session.createCriteria(Emp.class);
//            criteria.add(Restrictions.eq("deptno",20));
            criteria.add(Restrictions.eq("sal",1000));
            Emp emp= (Emp) criteria.uniqueResult();
            System.out.println(emp);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }finally {
            session.close();
        }
    }

}
