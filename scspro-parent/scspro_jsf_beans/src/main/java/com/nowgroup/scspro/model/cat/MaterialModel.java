package com.nowgroup.scspro.model.cat;

import java.util.ArrayList;
import java.util.List;

import com.nowgroup.scspro.dto.cat.Material;
import com.nowgroup.scspro.model.Modeleable;

public class MaterialModel extends Material implements Modeleable<Material> {
    private static final long serialVersionUID = -7867269938491034042L;
    private boolean selected;
    
    private List<String> images = new ArrayList<String>();
    
    @Override
    public Material demodelize() {
	Material result = new Material();

	result.setBaseLanguageDescription(this.getBaseLanguageDescription());
	result.setCommercialDescription(this.getCommercialDescription());
	result.setPurchaser(this.getPurchaser());
	result.setEnabled(this.isEnabled());
	result.setEnglishDescription(this.getEnglishDescription());
	result.setId(this.getId());
	result.setLabeling(this.getLabeling());
	result.setMaterialClass(this.getMaterialClass());
	result.setMaterialType(this.getMaterialType());
	result.setMeasurementUnit(this.getMeasurementUnit());
	result.setObservations(this.getObservations());
	result.setOrigin(this.getOrigin());
	result.setSedDescription(this.getSedDescription());
	result.setTariff(this.getTariff());
	result.setSeller(this.getSeller());

	return result;
    }

    @Override
    public void setSelected(boolean selected) {
	this.selected = selected;
    }

    @Override
    public boolean isSelected() {
	return selected;
    }

    @Override
    public Modeleable<Material> getModel(Material base) {
	MaterialModel result = new MaterialModel();

	result.setBaseLanguageDescription(base.getBaseLanguageDescription());
	result.setCommercialDescription(base.getCommercialDescription());
	result.setPurchaser(base.getPurchaser());
	result.setEnabled(base.isEnabled());
	result.setEnglishDescription(base.getEnglishDescription());
	result.setId(base.getId());
	result.setLabeling(base.getLabeling());
	result.setMaterialClass(base.getMaterialClass());
	result.setMaterialType(base.getMaterialType());
	result.setMeasurementUnit(base.getMeasurementUnit());
	result.setObservations(base.getObservations());
	result.setOrigin(base.getOrigin());
	result.setSedDescription(base.getSedDescription());
	result.setTariff(base.getTariff());
	result.setSeller(base.getSeller());

	result.setSelected(false);

	return result;
    }

    public List<String> getImages() {
	return images;
    }

    public void setImages(List<String> images) {
	this.images = images;
    }
}
