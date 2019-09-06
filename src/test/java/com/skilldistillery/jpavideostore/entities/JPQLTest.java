package com.skilldistillery.jpavideostore.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.skilldistillery.jpavideostore.client.JPQLLab;

class JPQLTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private JPQLLab jPQLLab;

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
		jPQLLab = new JPQLLab();
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		jPQLLab = null;
	}

	@Test
	@DisplayName("testing valid jPQLLab class method getRangeOfCustomers")
	public void testGetRangeOfCustomers() {
		assertEquals(3, jPQLLab.getRangeOfCustomers(1, 3).size());
	}
	
	@Test
	@DisplayName("testing valid jPQLLab class method getCustomerEmailByName")
	public void testGetCustomerEmailByName() {
		assertEquals("ROBIN.HAYES@sdvidcustomer.org", jPQLLab.getCustomerEmailByName("Robin", "Hayes"));
		assertNull(jPQLLab.getCustomerEmailByName("Peter", "Flamingo"));
	}
	
	@Test
	@DisplayName("testing valid jPQLLab class method getFilmByTitle")
	public void testGetFilmByTitle() {
		assertEquals(5, jPQLLab.getFilmByTitle("AFRICAN EGG").getId());
		assertNull(jPQLLab.getFilmByTitle("JAVA BEAN SANDWICH"));
	}
	
	@Test
	@DisplayName("testing valid jPQLLab class method getFilmsTitlesByReleaseYear")
	public void testGetFilmsTitlesByReleaseYear() {
		assertTrue(jPQLLab.getFilmsTitlesByReleaseYear(1999).size() > 0);
		assertNull(jPQLLab.getFilmsTitlesByReleaseYear(2099));
	}
}
