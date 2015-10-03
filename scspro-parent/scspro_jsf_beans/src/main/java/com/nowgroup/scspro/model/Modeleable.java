package com.nowgroup.scspro.model;

import com.nowgroup.scspro.dto.BaseDTO;

public interface Modeleable<T extends BaseDTO> {
    T demodelize();

    void setSelected(boolean selected);

    boolean isSelected();
    
    Modeleable<T> getModel(T base);
}
