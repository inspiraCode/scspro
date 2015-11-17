package com.nowgroup.scspro.dto.cat;

import java.util.Comparator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "cat_material", catalog = "supply_chain")
public class Material implements BaseDTO, Comparable<Material> {
    private static final long serialVersionUID = -498326688725664117L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MATERIAL_ID", nullable = false, unique = true)
    private int id;

    @IndexedEmbedded
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MATERIAL_PURCHASER", nullable = false)
    private Company purchaser;

    @IndexedEmbedded
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MATERIAL_SELLER", nullable = false)
    private Company seller;

    @Field
    @Enumerated(EnumType.STRING)
    @Column(name = "MATERIAL_TYPE", nullable = false)
    private MATERIAL_TYPE materialType = MATERIAL_TYPE.RAW_MATERIAL;

    @Field
    @Enumerated(EnumType.STRING)
    @Column(name = "MATERIAL_CLASS", nullable = false)
    private MATERIAL_CLASS materialClass = MATERIAL_CLASS.FINISHED_PRODUCT;

    @IndexedEmbedded
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MATERIAL_ORIGIN", nullable = false)
    private Country origin;

    @Field
    @Column(name = "MATERIAL_BASE_DESCRIPTION", nullable = false)
    private String baseLanguageDescription;

    @Field
    @Column(name = "MATERIAL_ENGLISH_DESCRIPTION", nullable = false)
    private String englishDescription;

    @Field
    @Column(name = "MATERIAL_COMMERCIAL_DESCRIPTION", nullable = false)
    private String commercialDescription;

    @Field
    @Column(name = "MATERIAL_SED_DESCRIPTION", nullable = false)
    private String sedDescription;

    @Field
    @Column(name = "MATERIAL_OBSERVATIONS", nullable = false)
    private String observations;

    @IndexedEmbedded
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MATERIAL_LABELING", nullable = false)
    private UNLabel labeling;

    @IndexedEmbedded
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MATERIAL_TARIFF", nullable = true)
    private Tariff tariff;

    @IndexedEmbedded
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MATERIAL_MEASUREMENT_UNIT", nullable = false)
    private MeasurementUnit measurementUnit;

    @Column(name = "MATERIAL_ENABLED", nullable = false)
    private boolean enabled = true;

    public enum MATERIAL_TYPE {
	RAW_MATERIAL, CONTAINER, PACKAGING, LABELING, LUBRICANT
    }

    public enum MATERIAL_CLASS {
	FIXED_ASSETS, RAW_MATERIAL, FINISHED_PRODUCT
    }

    public Company getPurchaser() {
	return purchaser;
    }

    public void setPurchaser(Company purchaser) {
	this.purchaser = purchaser;
    }

    public Company getSeller() {
	return seller;
    }

    public void setSeller(Company seller) {
	this.seller = seller;
    }

    public MATERIAL_TYPE getMaterialType() {
	return materialType;
    }

    public void setMaterialType(MATERIAL_TYPE materialType) {
	this.materialType = materialType;
    }

    public MATERIAL_CLASS getMaterialClass() {
	return materialClass;
    }

    public void setMaterialClass(MATERIAL_CLASS materialClass) {
	this.materialClass = materialClass;
    }

    public Country getOrigin() {
	return origin;
    }

    public void setOrigin(Country origin) {
	this.origin = origin;
    }

    public String getBaseLanguageDescription() {
	return baseLanguageDescription;
    }

    public void setBaseLanguageDescription(String baseLanguageDescription) {
	this.baseLanguageDescription = baseLanguageDescription;
    }

    public String getEnglishDescription() {
	return englishDescription;
    }

    public void setEnglishDescription(String englishDescription) {
	this.englishDescription = englishDescription;
    }

    public String getCommercialDescription() {
	return commercialDescription;
    }

    public void setCommercialDescription(String commercialDescription) {
	this.commercialDescription = commercialDescription;
    }

    public String getSedDescription() {
	return sedDescription;
    }

    public void setSedDescription(String sedDescription) {
	this.sedDescription = sedDescription;
    }

    public String getObservations() {
	return observations;
    }

    public void setObservations(String observations) {
	this.observations = observations;
    }

    public UNLabel getLabeling() {
	return labeling;
    }

    public void setLabeling(UNLabel labeling) {
	this.labeling = labeling;
    }

    public Tariff getTariff() {
	return tariff;
    }

    public void setTariff(Tariff tariff) {
	this.tariff = tariff;
    }

    public MeasurementUnit getMeasurementUnit() {
	return measurementUnit;
    }

    public void setMeasurementUnit(MeasurementUnit measurementUnit) {
	this.measurementUnit = measurementUnit;
    }

    public boolean isEnabled() {
	return enabled;
    }

    public void setEnabled(boolean enabled) {
	this.enabled = enabled;
    }

    @Override
    public int getId() {
	return this.id;
    }

    public void setId(int id) {
	this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
	if (obj == null)
	    return false;
	if (!(obj instanceof Material))
	    return false;

	Material temp = (Material) obj;
	return temp.getId() == id;
    }

    @Override
    public String toString() {
	return "{id:" + id + ";type:" + materialType + ";class:" + materialClass + ";description:" + baseLanguageDescription + ";}";
    }

    @Override
    public int compareTo(Material o) {
	return Comparators.BASE_DESCRIPTION.compare(this, o);
    }

    public static class Comparators {
	public static Comparator<Material> BASE_DESCRIPTION = new Comparator<Material>() {
	    public int compare(Material o1, Material o2) {
		return o1.baseLanguageDescription.compareTo(o2.baseLanguageDescription);
	    }
	};
	public static Comparator<Material> ENGLISH_DESCRIPTION = new Comparator<Material>() {
	    public int compare(Material o1, Material o2) {
		return o1.englishDescription.compareTo(o2.englishDescription);
	    }
	};
    }
}
