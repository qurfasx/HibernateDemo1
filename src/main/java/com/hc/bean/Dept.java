package com.hc.bean;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2016/12/22.
 */
@Entity
@Table(name="tb_Dept")//,uniqueConstraints = {@UniqueConstraint(columnNames ={"name","password"})}
public class Dept {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @TableGenerator(name = "deptGen",table = "tb_Gen",pkColumnName = "gen_key",
//            valueColumnName = "gen_value",pkColumnValue = "dept_id",allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.TABLE,generator = "deptGen")
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private int deptno;
    private String dname;
    private String loc;

    public int getDeptno() {
        return deptno;
    }

    public void setDeptno(int deptno) {
        this.deptno = deptno;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public Dept(String dname, String loc) {
        this.dname = dname;
        this.loc = loc;
    }

    public Dept() {

    }

    @Override
    public String toString() {
        return "Dept{" +
                "deptno=" + deptno +
                ", dname='" + dname + '\'' +
                ", loc='" + loc + '\'' +
                '}';
    }
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "dept")
    @Column(name = "deptno")
    private Set<Emp> emps = new HashSet<Emp>();

    public Set<Emp> getEmps() {
        return emps;
    }

    public void setEmps(Set<Emp> emps) {
        this.emps = emps;
    }
}
