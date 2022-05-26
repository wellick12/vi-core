package com.vehicle.identifier.vicore.models;

import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Where(clause = "deleted is null")
@Table(name = "settings")
public class Setting extends Base {

	private String description;
	@Column(unique = true, nullable = false)
	private String key;
	@Column(columnDefinition = "TEXT")
	private String value;

	public String getDescription() {

		return description;
	}

	public void setDescription(String description) {

		this.description = description;
	}

	public String getKey() {

		return key;
	}

	public void setKey(String key) {

		this.key = key;
	}

	public String getValue() {

		return value;
	}

	public void setValue(String value) {

		this.value = value;
	}
}
