package com.nowgroup.scspro.dto.cat;

import java.util.Comparator;
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
import org.hibernate.search.annotations.IndexedEmbedded;

import com.nowgroup.scspro.dto.BaseDTO;

@Indexed
@Entity
@Table(name = "cat_tariff", catalog = "supply_chain", uniqueConstraints = { @UniqueConstraint(columnNames = "TARIFF_CODE") })
public class Tariff implements BaseDTO, Comparable<Tariff> {
    private static final long serialVersionUID = -680104441477774578L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TARIFF_ID", nullable = false, unique = true)
    private int id;

    @Field
    @Column(name = "TARIFF_CODE", nullable = false, unique = true)
    private String code;

    @IndexedEmbedded
    @OneToMany(mappedBy = "tariff", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TariffValidity> validities = new HashSet<TariffValidity>();

    @Field
    @Column(name = "TARIFF_DESCRIPTION", nullable = false)
    private String description;

    @Field
    @Column(name = "TARIFF_DOCUMENTOR_DESCRIPTION", nullable = false)
    private String documentorDescription;

    @Field
    @Column(name = "TARIFF_OBSERVATIONS", nullable = false)
    private String observations;

    @Override
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

    public Set<TariffValidity> getValidities() {
	return validities;
    }

    public void setValidities(Set<TariffValidity> validities) {
	this.validities = validities;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public String getDocumentorDescription() {
	return documentorDescription;
    }

    public void setDocumentorDescription(String documentorDescription) {
	this.documentorDescription = documentorDescription;
    }

    public String getObservations() {
	return observations;
    }

    public void setObservations(String observations) {
	this.observations = observations;
    }

    @Override
    public boolean equals(Object obj) {
	if (obj == null)
	    return false;
	if (!(obj instanceof Tariff))
	    return false;

	Tariff temp = (Tariff) obj;
	return temp.getId() == id;
    }

    @Override
    public String toString() {
	return "{id:" + id + ";code:" + code + ";description:" + description + ";}";
    }

    @Override
    public int compareTo(Tariff o) {
	return Comparators.CODE.compare(this, o);
    }

    public static class Comparators {
	public static Comparator<Tariff> CODE = new Comparator<Tariff>() {
	    public int compare(Tariff o1, Tariff o2) {
		return o1.code.compareTo(o2.code);
	    }
	};
	public static Comparator<Tariff> DESCRIPTION = new Comparator<Tariff>() {
	    public int compare(Tariff o1, Tariff o2) {
		return o1.description.compareTo(o2.description);
	    }
	};
    }

}
