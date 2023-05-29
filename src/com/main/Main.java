package com.main;

import java.util.ArrayList;
import java.util.List;

import com.dao.CityCountryDAO;
import com.dao.CityCountryDAOImpl;
import com.entity.City;
import com.entity.Country;

public class Main {

	public static void main(String[] args) {

		// creao un istanza del interfaccia
		CityCountryDAO dao = new CityCountryDAOImpl();

		// creao un nuovo country chiamato roccasecca
		Country latina = new Country("Roccasecca");

		// creao una lista di città vuota

		List<City> city = new ArrayList<>();

		// creao due città vallemarina e msb
		City vallermarina = new City("pippo");
		City msb = new City("baudo");
		// aggiungo le città appena create nella lista di città
		city.add(vallermarina);
		city.add(msb);

		// utilizzo il metodo fa me creato che mi permette di inserire nel database una
		// lista di city appartententi ad un country
		dao.insertCityCountry(latina, city);

		/*
		 * provo update e fa fatto un costruttore con country id per indicare quale va
		 * modificato
		 */

		latina = new Country(120, "Roccasecca6");
		dao.updateCountry(latina);
		System.out.println(dao.getCity(609).getCountry().getCountry());

		dao.removeCountry(120);
	}

}
