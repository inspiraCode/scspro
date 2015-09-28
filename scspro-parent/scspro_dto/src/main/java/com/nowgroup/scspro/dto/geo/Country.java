package com.nowgroup.scspro.dto.geo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import com.nowgroup.scspro.dto.BaseDTO;

@Indexed
@Entity
@Table(name = "geo_country", catalog = "supply_chain", uniqueConstraints = { @UniqueConstraint(columnNames = "COUNTRY_CODE") })
public class Country implements BaseDTO {
    private static final long serialVersionUID = 4989747630427434702L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COUNTRY_ID", unique = true, nullable = false)
    private int id;

    @Field
    @Column(name = "COUNTRY_CODE", unique = true, nullable = false)
    private String code;

    @Field
    @Column(name = "COUNTRY_NAME", nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "country", cascade = CascadeType.ALL)
    @Column(name = "STATE_COUNTRY")
    private Set<State> states = new HashSet<State>(0);

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getCode() {
	return code;
    }

    public void setCode(String code) {
	this.code = code;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public Set<State> getStates() {
	return states;
    }

    public void setStates(Set<State> states) {
	this.states = states;
    }
}
