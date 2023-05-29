package com.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;

/**
 * The persistent class for the city database table.
 * 
 */
@Entity
@NamedQuery(name = "City.findAll", query = "SELECT c FROM City c")
public class City implements Serializable {
	private static final long serialVersionUID = 1L;

	private String city;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "city_id")
	private int cityId;

	@UpdateTimestamp
	@Column(name = "last_update")
	private Timestamp lastUpdate;

	/*
	 * Stiamo dichiarando un'associazione many-to-one tra l'entità corrente e
	 * un'altra entità "Country". In particolare, stiamo specificando che l'entità
	 * corrente, che include questa dichiarazione, ha una relazione many-to-one con
	 * l'entità "Country", utilizzando la chiave esterna "country_id" come
	 * riferimento. Questo significa che un'istanza dell'entità corrente può avere
	 * una sola istanza dell'entità "Country" associata ad essa, ma l'entità
	 * "Country" può essere associata a molte istanze dell'entità corrente.
	 */
	@ManyToOne
	@JoinColumn(name = "country_id")
	private Country country;

	public City() {
	}

	// Costruttore per l'update
	public City(String city, int cityId) {
		this.city = city;
		this.cityId = cityId;

	}

	// Costruttore per l'insert
	public City(String city) {
		this.city = city;

	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getCityId() {
		return this.cityId;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Country getCountry() {
		return country;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}