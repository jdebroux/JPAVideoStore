package com.skilldistillery.jpavideostore.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AddressTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Address adr;

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
		adr = em.find(Address.class, 5);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		adr = null;
	}

	@Test
	@DisplayName("testing valid Address class annotations")
	public void test1() {
		assertEquals("1913 Hanoi Way", adr.getStreet());
		assertEquals("Sasebo", adr.getCity());
		assertEquals("35200",adr.getPostalCode());
		assertEquals("28303384290",adr.getPhone());
	}
}
