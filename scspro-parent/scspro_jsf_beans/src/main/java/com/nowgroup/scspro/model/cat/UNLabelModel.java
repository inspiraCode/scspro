package com.nowgroup.scspro.model.cat;

import com.nowgroup.scspro.dto.cat.UNLabel;
import com.nowgroup.scspro.model.Modeleable;

public class UNLabelModel extends UNLabel implements Modeleable<UNLabel> {
    private static final long serialVersionUID = 8026385369415702431L;

    private boolean selected;

    @Override
    public UNLabel demodelize() {
	UNLabel label = new UNLabel();

	label.setId(getId());
	label.setBulk(getBulk());
	label.setCargoAircraftOnly(getCargoAircraftOnly());
	label.setDescriptions(getDescriptions());
	label.setExceptions(getExceptions());
	label.setHazardScore(getHazardScore());
	label.setHazardType(getHazardType());
	label.setLabels(getLabels());
	label.setMatterName(getMatterName());
	label.setNiu(getNiu());
	label.setNonBulk(getNonBulk());
	label.setNumbers(getNumbers());
	label.setPassengerAircraftRail(getPassengerAircraftRail());
	label.setPg(getPg());
	label.setSpecial(getSpecial());
	label.setVesselLocation(getVesselLocation());

	return label;
    }

    @Override
    public void setSelected(boolean selected) {
	this.selected = selected;
    }

    @Override
    public boolean isSelected() {
	return selected;
    }

}
