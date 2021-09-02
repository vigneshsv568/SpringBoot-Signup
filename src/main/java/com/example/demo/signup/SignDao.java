package com.example.demo.signup;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SignDao {
	@Autowired
	EntityManager em;
	
	@Autowired
	SignRepo repo;
	
	

	public List<SignEntity> check(String email) {
		StringBuilder buf = new StringBuilder(" select c from SignEntity c");
		buf.append(" where c.email='"+email+"'");

		javax.persistence.Query query = em.createQuery(buf.toString());

		return query.getResultList();
		
	}



	public void save(SignEntity obj) {
		repo.save(obj);
		
	}

	
	

}
