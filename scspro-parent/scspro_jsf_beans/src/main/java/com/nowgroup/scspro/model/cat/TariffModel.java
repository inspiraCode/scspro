package com.nowgroup.scspro.model.cat;

import com.nowgroup.scspro.dto.cat.Tariff;
import com.nowgroup.scspro.model.Modeleable;

public class TariffModel extends Tariff implements Modeleable<Tariff> {
    private static final long serialVersionUID = -457758990885833277L;
    private boolean selected;

    @Override
    public void setSelected(boolean selected) {
	this.selected = selected;
    }

    @Override
    public boolean isSelected() {
	return selected;
    }

    @Override
    public Tariff demodelize() {
	Tariff result = new Tariff();

	result.setId(this.getId());
	result.setDescription(this.getDescription());
	result.setCode(this.getCode());
	result.setDocumentorDescription(this.getDocumentorDescription());
	result.setObservations(this.getObservations());

	result.setValidities(this.getValidities());

	return result;
    }

    @Override
    public Modeleable<Tariff> getModel(Tariff base) {
	TariffModel result = new TariffModel();

	result.setCode(base.getCode());
	result.setDescription(base.getDescription());
	result.setDocumentorDescription(base.getDocumentorDescription());
	result.setId(base.getId());
	result.setObservations(base.getObservations());
	result.setSelected(false);
	result.setValidities(base.getValidities());

	return result;
    }
}
