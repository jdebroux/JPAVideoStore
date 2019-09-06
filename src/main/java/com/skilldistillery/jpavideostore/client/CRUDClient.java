package com.skilldistillery.jpavideostore.client;

import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.skilldistillery.jpavideostore.entities.Address;
import com.skilldistillery.jpavideostore.entities.Customer;

public class CRUDClient {
	private static EntityManagerFactory emf;
	static {
		emf = Persistence.createEntityManagerFactory("VideoStore");
	}

	public static void main(String[] args) {
		CRUDClient crud = new CRUDClient();
		crud.deleteAddress(730);
	}

	public void updateNullEmails() {
		EntityManager em = emf.createEntityManager();
		String qS = "SELECT cust FROM Customer cust WHERE cust.email IS NULL OR cust.email LIKE ''";
		Stream<Customer> customers = em.createQuery(qS, Customer.class).getResultStream();
		em.getTransaction().begin();
		customers.forEach((customer) -> {
			customer.setEmail(customer.getFirstName() + "." + customer.getLastName() + "@sdcustomer.org");
		});
		em.flush();
		em.getTransaction().commit();
		em.close();
	}

	public void addNewAddress() {
		EntityManager em = emf.createEntityManager();
		Address myAddress = new Address("3250 s Newcombe st", null, "Lakewood", "Colorado", "80227", "80227");
		em.getTransaction().begin();
		em.persist(myAddress);
		em.flush();
		em.getTransaction().commit();
		em.close();
		System.out.println(myAddress);
	}

	private void deleteAddress(int id) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.remove(em.find(Address.class, id));
		em.flush();
		em.getTransaction().commit();
		em.close();
	}
}
