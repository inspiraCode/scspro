package com.nowgroup.scspro.dto.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.nowgroup.scspro.dto.BaseDTO;

@Entity
@Table(name = "sys_role", catalog = "supply_chain", uniqueConstraints = { @UniqueConstraint(columnNames = "ROLE_NAME") })
public class Role implements BaseDTO {
    private static final long serialVersionUID = 5918579335472373777L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROLE_ID", unique = true, nullable = false)
    private int id;

    @Column(name = "ROLE_NAME", unique = true, nullable = false)
    private String name;

    public Role() {
    }

    public Role(String name) {
	this.name = name;
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
	if (obj == null)
	    return false;
	if (!this.getClass().equals(obj.getClass()))
	    return false;

	Role oRole = (Role) obj;
	if (this.id == oRole.getId() || this.name == oRole.getName())
	    return true;
	return false;
    }

    @Override
    public int hashCode() {
	int tmp = 0;
	tmp = (id + name).hashCode();
	return tmp;
    }

    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder();
	sb.append("{");
	sb.append(this.getId() + ", ");
	sb.append("'" + this.getName() + "'");
	sb.append("}");
	return sb.toString();
    }
}
