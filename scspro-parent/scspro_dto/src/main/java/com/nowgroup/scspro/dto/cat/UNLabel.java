package com.nowgroup.scspro.dto.cat;

import java.util.Comparator;

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
@Table(name = "cat_un", catalog = "supply_chain", uniqueConstraints = { @UniqueConstraint(columnNames = "UN_NIU") })
public class UNLabel implements BaseDTO, Comparable<UNLabel> {
    private static final long serialVersionUID = 4286345478537986375L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UN_ID", nullable = true, unique = true)
    private int id;

    @Field
    @Column(name = "UN_NIU", nullable = false, unique = true)
    private int niu;

    @Field
    @Column(name = "UN_NUMBERS", nullable = true)
    private String numbers;

    @Field
    @Column(name = "UN_DESCRIPTIONS", nullable = true)
    private String descriptions;

    @Field
    @Column(name = "UN_MATTER_NAME", nullable = true)
    private String matterName;

    @Field
    @Column(name = "UN_LABELS", nullable = true)
    private String labels;

    @Field
    @Column(name = "UN_CARGO_AIRCRAFT_ONLY", nullable = true)
    private String cargoAircraftOnly;

    @Field
    @Column(name = "UN_VESSEL_LOCATION", nullable = true)
    private String vesselLocation;

    @Field
    @Column(name = "UN_PASSENGER_AIRCRAFT_RAIL", nullable = true)
    private String passengerAircraftRail;

    @Field
    @Column(name = "UN_SPECIAL", nullable = true)
    private String special;

    @Field
    @Column(name = "UN_PG", nullable = true)
    private String pg;

    @Field
    @Column(name = "UN_BULK", nullable = true)
    private String bulk;

    @Field
    @Column(name = "UN_NON_BULK", nullable = true)
    private String nonBulk;

    @Field
    @Column(name = "UN_EXCEPTIONS", nullable = true)
    private String exceptions;

    @Field
    @Column(name = "UN_HAZARD_SCORE", nullable = true)
    private int hazardScore;

    @Field
    @Column(name = "UN_HAZARD_TYPE", nullable = true)
    private String hazardType;

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public int getNiu() {
	return niu;
    }

    public void setNiu(int niu) {
	this.niu = niu;
    }

    public String getNumbers() {
	return numbers;
    }

    public void setNumbers(String numbers) {
	this.numbers = numbers;
    }

    public String getDescriptions() {
	return descriptions;
    }

    public void setDescriptions(String descriptions) {
	this.descriptions = descriptions;
    }

    public String getMatterName() {
	return matterName;
    }

    public void setMatterName(String matterName) {
	this.matterName = matterName;
    }

    public String getLabels() {
	return labels;
    }

    public void setLabels(String labels) {
	this.labels = labels;
    }

    public String getCargoAircraftOnly() {
	return cargoAircraftOnly;
    }

    public void setCargoAircraftOnly(String cargoAircraftOnly) {
	this.cargoAircraftOnly = cargoAircraftOnly;
    }

    public String getVesselLocation() {
	return vesselLocation;
    }

    public void setVesselLocation(String vesselLocation) {
	this.vesselLocation = vesselLocation;
    }

    public String getPassengerAircraftRail() {
	return passengerAircraftRail;
    }

    public void setPassengerAircraftRail(String passengerAircraftRail) {
	this.passengerAircraftRail = passengerAircraftRail;
    }

    public String getSpecial() {
	return special;
    }

    public void setSpecial(String special) {
	this.special = special;
    }

    public String getPg() {
	return pg;
    }

    public void setPg(String pg) {
	this.pg = pg;
    }

    public String getBulk() {
	return bulk;
    }

    public void setBulk(String bulk) {
	this.bulk = bulk;
    }

    public String getNonBulk() {
	return nonBulk;
    }

    public void setNonBulk(String nonBulk) {
	this.nonBulk = nonBulk;
    }

    public String getExceptions() {
	return exceptions;
    }

    public void setExceptions(String exceptions) {
	this.exceptions = exceptions;
    }

    public int getHazardScore() {
	return hazardScore;
    }

    public void setHazardScore(int hazardScore) {
	this.hazardScore = hazardScore;
    }

    public String getHazardType() {
	return hazardType;
    }

    public void setHazardType(String hazardType) {
	this.hazardType = hazardType;
    }

    @Override
    public int compareTo(UNLabel o) {
	return Comparators.NAME.compare(this, o);
    }

    public static class Comparators {
	public static Comparator<UNLabel> NAME = new Comparator<UNLabel>() {
	    @Override
	    public int compare(UNLabel o1, UNLabel o2) {
		return o1.matterName.compareTo(o2.matterName);
	    }
	};

	public static Comparator<UNLabel> NUMBERS = new Comparator<UNLabel>() {
	    @Override
	    public int compare(UNLabel o1, UNLabel o2) {
		return o1.numbers.compareTo(o2.numbers);
	    }
	};
    }
}
