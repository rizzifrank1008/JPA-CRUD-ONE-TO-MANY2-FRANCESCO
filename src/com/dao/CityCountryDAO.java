package com.dao;

import java.util.List;

import com.entity.City;
import com.entity.Country;

public interface CityCountryDAO {

	public void insertCityCountry(Country country, List<City> city);

	public void updateCountry(Country country);

	public void removeCountry(int countryID);

	public City getCity(int cityId);
}
