package com.skilldistillery.jpavideostore.data;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.Transactional;

import com.skilldistillery.jpavideostore.entities.Actor;

@Transactional
public class ActorDAOImpl implements ActorDAO {

	private static EntityManagerFactory emf;
	
	static {
		emf = Persistence.createEntityManagerFactory("VideoStore");
	}
	@Override
	public Actor create(Actor actor) {
		EntityManager em = emf.createEntityManager();
	    em.getTransaction().begin();
	    em.persist(actor);
	    em.flush();
	    em.getTransaction().commit();
	    em.close();
	    return actor;
	  }
	@Override
	public Actor update(int id, Actor actor) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Actor changedActor = em.find(Actor.class, id);
		changedActor.setFirstName(actor.getFirstName());
		changedActor.setLastName(actor.getLastName());
		em.flush();
		em.getTransaction().commit();
		em.close();
		return changedActor;
	}
	@Override
	public boolean destory(int id) {
		EntityManager em = emf.createEntityManager();
		boolean status = false;
		Actor actor = em.find(Actor.class, id);
		em.getTransaction().begin();
		em.remove(actor);
		em.flush();
		em.getTransaction().commit();
		status = em.contains(actor);
		em.close();
		return !status;
	}
}
