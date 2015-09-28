package com.nowgroup.scspro.dao;

import java.util.List;

import com.nowgroup.scspro.dto.BaseDTO;

public interface BaseDAO<T extends BaseDTO> {
    /**
    * Retrieve an instance of Dominable object by it's ID.
    * @param id
    * @return
    */
    T get(int id);

    /**
     * Retrieve all dominable records from database.
     * @return
     */
    List<T> getAll();

    /**
     * Save and return its ID
     * @param object
     * @return
     */
    int add(T object);

    /**
     * Execute update.
     * @param object
     */
    void update(T object);

    /**
     * Remove a dominable from database.
     * @param object
     */
    void delete(T object);
}
