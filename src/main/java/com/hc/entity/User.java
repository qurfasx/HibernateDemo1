package com.hc.entity;

import javax.persistence.*;

/**
 * Created by Administrator on 2016/12/22.
 */
@Entity
@Table(name = "tb_user", schema = "test3", catalog = "")
public class User {
    @Id
    @Column(name = "name",columnDefinition = "Char(8),check(name in ('bb','aa','cc') ")
    @TableGenerator(name = "userGen",table = "tb_userGen",
    pkColumnName = "gen_key",
    valueColumnName = "gen_value",
    pkColumnValue = "user_id",
    allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "userGen")
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**GenerationType.TABLE(table)
     * GenerationType.AUTO()
     * GenerationType.IDENTITY(只生成一个表)
     * GenerationType.SEQUENCE
     */

    private  int id;
    private String name;
    private  String password;
    private String pass;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Basic
    @Column(name = "pass")
    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (pass != null ? !pass.equals(user.pass) : user.pass != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (pass != null ? pass.hashCode() : 0);
        return result;
    }
}
