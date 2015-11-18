package com.nowgroup.scspro.dto.cat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import com.nowgroup.scspro.dto.BaseDTO;
import com.nowgroup.scspro.dto.geo.Country;

@Indexed
@Entity
@Table(name = "cat_tariff_validity", catalog = "supply_chain")
public class TariffValidity implements BaseDTO {
    private static final long serialVersionUID = 2621119845044783877L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TARIFF_VALIDITY_ID", nullable = false, unique = true)
    private int id;

    @IndexedEmbedded
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TARIFF_VALIDITY_COUNTRY", nullable = false)
    private Country country;

    @IndexedEmbedded
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "TARIFF_VALIDITY_MU", nullable = false)
    private MeasurementUnit measurementUnit;

    @Field
    @Column(name = "TARIFF_VALIDITY_DESCRIPTION", nullable = false)
    private String description;

    @IndexedEmbedded
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "TARIFF_ID", nullable = false)
    private Tariff tariff;

    @Override
    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public Country getCountry() {
	return country;
    }

    public void setCountry(Country country) {
	this.country = country;
    }

    public MeasurementUnit getMeasurementUnit() {
	return measurementUnit;
    }

    public void setMeasurementUnit(MeasurementUnit measurementUnit) {
	this.measurementUnit = measurementUnit;
    }

    @Override
    public boolean equals(Object obj) {
	if (obj == null)
	    return false;
	if (!(obj instanceof TariffValidity))
	    return false;

	TariffValidity temp = (TariffValidity) obj;
	if (this.id == temp.getId())
	    return true;

	return false;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public Tariff getTariff() {
	return tariff;
    }

    public void setTariff(Tariff tariff) {
	this.tariff = tariff;
    }
}
