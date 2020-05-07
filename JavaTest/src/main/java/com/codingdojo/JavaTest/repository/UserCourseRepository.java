package com.codingdojo.JavaTest.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.JavaTest.models.UserCourse;

@Repository
public interface UserCourseRepository extends CrudRepository <UserCourse, Long> {
	List<UserCourse> findAll();
}
