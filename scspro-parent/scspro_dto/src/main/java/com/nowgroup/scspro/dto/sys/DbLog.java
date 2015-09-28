package com.nowgroup.scspro.dto.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import com.nowgroup.scspro.dto.BaseDTO;

@Indexed
@Entity
@Table(name = "sys_log", catalog = "supply_chain")
public class DbLog implements BaseDTO {
    private static final long serialVersionUID = 1021664860630497176L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EVENT_ID", nullable = false, unique = true)
    private int id;

    @Field(analyze = Analyze.YES)
    @Column(name = "EVENT_DETAIL", nullable = false, columnDefinition = "TEXT")
    private String detail;

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getDetail() {
	return detail;
    }

    public void setDetail(String detail) {
	this.detail = detail;
    }
}
