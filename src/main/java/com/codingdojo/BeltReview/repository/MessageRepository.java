package com.codingdojo.BeltReview.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.BeltReview.models.Message;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long>{

}
