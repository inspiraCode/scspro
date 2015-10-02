package com.nowgroup.scspro.service;

import java.util.List;

import com.nowgroup.scspro.dto.BaseDTO;

public interface BaseService<T extends BaseDTO> {
    T get(int id);

    List<T> getAll();

    int add(T object);

    void update(T object);

    void delete(T object);
    
    void deleteAll(List<T> items);
}
