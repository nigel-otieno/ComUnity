package com.codingdojo.JavaTest.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.codingdojo.JavaTest.models.Course;

public interface CourseRepository extends CrudRepository<Course, Long>  {
	List<Course> findAll();

}
