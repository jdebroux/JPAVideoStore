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

class StaffTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Staff staff;

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
		staff = em.find(Staff.class, 2);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		staff = null;
	}
	
	@Test
	@DisplayName("testing valid Staff class annotations")
	public void testStaffAnnotations() {
		assertEquals("Lisa", staff.getFirstName());
		assertEquals("Gibson", staff.getLastName());
		assertEquals("lisa.gibson@example.com", staff.getEmail());
	}

}
