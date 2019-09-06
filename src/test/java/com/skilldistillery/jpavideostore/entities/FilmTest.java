package com.skilldistillery.jpavideostore.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FilmTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Film film;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("VideoStore");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		film = em.find(Film.class, 5);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		film = null;
	}

	@Test
	@DisplayName("testing valid Film class annotations")
	public void testFilmAnnotations() {
		assertEquals("AFRICAN EGG", film.getTitle());
		assertEquals(
				"A Fast-Paced Documentary of a Pastry Chef And a Dentist who must Pursue a Forensic Psychologist in The Gulf of Mexico",
				film.getDescription());
		assertEquals(1993, film.getReleaseYear());
		assertEquals(2.99, film.getRentalRate(), .001);
		assertEquals(130, film.getLength());
		assertEquals(22.99, film.getReplacementCost(), .001);
	}
	
	@Test
	@DisplayName("testing films for ratings annotations")
	public void films_have_ratings() {
		assertEquals(Rating.G, film.getRating());
	}
}
