package com.dao;

import java.util.List;

import com.entity.City;
import com.entity.Country;
import com.provider.ProviderManager;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.RollbackException;

public class CityCountryDAOImpl implements CityCountryDAO {

	/*
	 * l'EntityManagerFactory e l'EntityManager sono fondamentali per l'interazione
	 * tra l'applicazione e il database, e sono utilizzati per gestire le operazioni
	 * di lettura/scrittura dei dati e la comunicazione tra queste due entità.
	 * 
	 */
	private EntityManagerFactory emf;
	private EntityManager em;

	@Override
	public void insertCityCountry(Country country, List<City> city) {
		initRoutine();
		// valorizzo i campi relazionali sia del parent che dei child
		city.forEach(c -> c.setCountry(country));
		country.setCity(city);
		// faccio l'insert SOLO del parent (grazie alla CascadeType.PERSIST)
		em.persist(country);
		closeRoutine();

	}

	@Override
	public void updateCountry(Country country) {
		initRoutine();
		em.merge(country);
		closeRoutine();

	}

	@Override
	public void removeCountry(int countryID) {
		initRoutine();
		// questa volta non c'e' bisogno di iterare sulla lista di child
		// perche' il parent ha come politica on cascade la delete dei child
		em.remove(em.find(Country.class, countryID));
		closeRoutine();

	}

	@Override
	public City getCity(int cityId) {
		initRoutine();
		City city = em.find(City.class, cityId);
		closeRoutine();
		return city;
	}

	/*
	 * racchiudo i metodi del provider manger in due metodi uno di inizio e uno di
	 * chiusura in modo da fare ogni volta una transazione per query
	 */
	private void initRoutine() {
		emf = ProviderManager.getEntityManagerFactory();
		em = ProviderManager.getEntityManager(emf);
		ProviderManager.beginTransaction(em);
	}

	private void closeRoutine() {
		try {
			ProviderManager.commitTransaction(em);
		} catch (RollbackException rbe) {
			System.err.println("Transazione fallita: eseguo il rollback.");
			rbe.printStackTrace();
			ProviderManager.rollbackTransaction(em);
		} finally {
			ProviderManager.closeTransaction(em);
			ProviderManager.closeEntityManagerFactory(emf);
		}
	}

}
