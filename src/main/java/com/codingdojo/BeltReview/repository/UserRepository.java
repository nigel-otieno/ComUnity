package com.codingdojo.BeltReview.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.BeltReview.models.Event;
import com.codingdojo.BeltReview.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	User findByEmail(String email);
	List<User> findAll();
//	Event findByName(String name);
	List<User> findByNameContaining(String search);
}
