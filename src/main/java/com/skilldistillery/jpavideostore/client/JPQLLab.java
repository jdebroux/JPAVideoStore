package com.skilldistillery.jpavideostore.client;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.skilldistillery.jpavideostore.entities.Film;

public class JPQLLab {
	private static EntityManagerFactory emf;
	private static EntityManager em;

	static {
		emf = Persistence.createEntityManagerFactory("VideoStore");
		em = emf.createEntityManager();
	}
	
	public static void main(String[] args) {
		JPQLLab jPQLLab = new JPQLLab();

		List<Object[]> results = jPQLLab.getRangeOfCustomers(100, 110);

		for (Object[] obj : results) {
			System.out.println("First Name: " + obj[0] + "\tLast Name: " + obj[1] + "\tEmail: " + obj[2]);
		}

		em.close();
		emf.close();
	}

	public List<Object[]> getRangeOfCustomers(int lower, int upper) {
		String qS = "SELECT customer.firstName, customer.lastName, customer.email FROM Customer customer WHERE customer.id BETWEEN :lower AND :upper";
		List<Object[]> results = em.createQuery(qS, Object[].class)
				 				   .setParameter("lower", lower)
				 				   .setParameter("upper", upper)
				 				   .getResultList();
		return results;
	}
	
	public String getCustomerEmailByName(String fname, String lname){
		String qS = "SELECT cust.email FROM Customer cust WHERE cust.firstName = :fname AND cust.lastName = :lname";
		String email = null;
		List<String> result = em.createQuery(qS, String.class)
								.setParameter("fname", fname)
								.setParameter("lname", lname)
								.getResultList();
		if(result != null && result.size() > 0) {
			email = result.get(0);
		}
		return email;
	}
	
	public Film getFilmByTitle(String filmTitle) {
		Film film = null;
		String qS = "SELECT film FROM Film film WHERE film.title = :thisFilmTitle";
		List<Film> films = em.createQuery(qS, Film.class).setParameter("thisFilmTitle", filmTitle).getResultList();
		if(films != null && films.size() > 0) {
			film = films.get(0);
		}
		return film;
	}
	public List<String> getFilmsTitlesByReleaseYear(int year) {
		String qS = "SELECT film.title FROM Film film WHERE film.releaseYear = :thisReleaseYear";
		List<String> filmTitles = em.createQuery(qS, String.class).setParameter("thisReleaseYear", year).getResultList();
		if(filmTitles != null && filmTitles.size() > 0) {
			return filmTitles;
		}else {
			return null;
		}
	}
}
