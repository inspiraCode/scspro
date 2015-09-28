package com.nowgroup.scspro.dto.geo;

import java.util.Comparator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import com.nowgroup.scspro.dto.BaseDTO;

@Indexed
@Entity
@Table(name = "geo_state", catalog = "supply_chain", uniqueConstraints = { @UniqueConstraint(columnNames = { "STATE_NAME", "STATE_COUNTRY" }) })
public class State implements BaseDTO, Comparable<State> {
    private static final long serialVersionUID = -5205132724577261015L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STATE_ID")
    private int id;

    @Field
    @Column(name = "STATE_NAME", nullable = false)
    private String name;

    @Field
    @Column(name = "STATE_CODE", nullable = false)
    private String code;

    @IndexedEmbedded
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STATE_COUNTRY", nullable = false)
    private Country country;

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

    public String getCode() {
	return code;
    }

    public void setCode(String code) {
	this.code = code;
    }

    public Country getCountry() {
	return country;
    }

    public void setCountry(Country country) {
	this.country = country;
    }

    @Override
    public boolean equals(Object obj) {
	if (obj == null)
	    return false;
	if (!this.getClass().equals(obj.getClass()))
	    return false;

	State oState = (State) obj;
	if (this.id == oState.getId())
	    return true;
	return false;
    }

    @Override
    public int hashCode() {
	int tmp = 0;
	tmp = (id + code).hashCode();
	return tmp;
    }

    @Override
    public String toString() {
	return "{id:" + id + ";name:" + name + ";code:" + code + ";}";
    }

    @Override
    public int compareTo(State o) {
	return Comparators.NAME.compare(this, o);
    }

    public static class Comparators {
	public static Comparator<State> NAME = new Comparator<State>() {

	    public int compare(State o1, State o2) {
		return o1.name.compareTo(o2.name);
	    }
	};
    }
}
