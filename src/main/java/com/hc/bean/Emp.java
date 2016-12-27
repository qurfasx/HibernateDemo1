package com.hc.bean;


import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "tb_emp")
@NamedQuery(name = "queryAll",query = "from Emp")
public class Emp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @TableGenerator(name = "empGen",table = "tb_Gen",pkColumnName = "gen_key",valueColumnName = "gen_value",pkColumnValue = "emp_id",allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.TABLE,generator = "empGen")
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer empno;
    private String ename;

    private String job;

    private Integer mgr;

    private Date hiredate;

    private Float sal;

    private Float comm;

    public Emp(Integer empno,String ename, Float sal, Dept dept) {
        this.empno =empno;
        this.ename = ename;
        this.sal = sal;
        this.dept = dept;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "empno=" + empno +
                ", ename='" + ename + '\'' +
                ", job='" + job + '\'' +
                ", mgr=" + mgr +
                ", hiredate=" + hiredate +
                ", sal=" + sal +
                ", comm=" + comm +
                '}';
    }

    public Integer getEmpno() {
        return empno;
    }

    public void setEmpno(Integer empno) {
        this.empno = empno;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename == null ? null : ename.trim();
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job == null ? null : job.trim();
    }

    public Integer getMgr() {
        return mgr;
    }

    public void setMgr(Integer mgr) {
        this.mgr = mgr;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public Float getSal() {
        return sal;
    }

    public void setSal(Float sal) {
        this.sal = sal;
    }

    public Float getComm() {
        return comm;
    }

    public void setComm(Float comm) {
        this.comm = comm;
    }

    public Emp() {
    }

    public Emp(String ename, String job, Integer mgr, Date hiredate, Float sal, Float comm) {
        this.ename = ename;
        this.job = job;
        this.mgr = mgr;
        this.hiredate = hiredate;
        this.sal = sal;
        this.comm = comm;
    }

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "deptno")
    private Dept dept;

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }
}
