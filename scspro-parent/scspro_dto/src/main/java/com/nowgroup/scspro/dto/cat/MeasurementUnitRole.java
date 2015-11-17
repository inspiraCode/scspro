package com.nowgroup.scspro.dto.cat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import com.nowgroup.scspro.dto.BaseDTO;

@Indexed
@Entity
@Table(name = "sys_measurement_unit_role", catalog = "supply_chain", uniqueConstraints = { @UniqueConstraint(columnNames = "MU_ROLE_NAME") })
public class MeasurementUnitRole implements BaseDTO {
    private static final long serialVersionUID = -2817028189475229567L;

    public static final String WEIGHT_USAGE = "weight";
    public static final String PACK_USAGE = "pack";
    public static final String LOAD_USAGE = "load";
    public static final String LENGTH_USAGE = "length";
    public static final String AREA_USAGE = "area";
    public static final String VOLUME_USAGE = "volume";
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MU_ROLE_ID", nullable = false, unique = true)
    private int id;

    @Field
    @Column(name = "MU_ROLE_NAME", nullable = false, unique = true)
    private String name;

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
    public String toString() {
	return "{id:" + id + ";name:" + name + ";}";
    }

    @Override
    public boolean equals(Object obj) {
	if (obj == null)
	    return false;
	if (!(obj instanceof MeasurementUnitRole))
	    return false;

	MeasurementUnitRole oRole = (MeasurementUnitRole) obj;
	if (this.id == oRole.getId())
	    return true;
	return false;
    }

    @Override
    public int hashCode() {
	int tmp = 0;
	tmp = (id + name).hashCode();
	return tmp;
    }
}
