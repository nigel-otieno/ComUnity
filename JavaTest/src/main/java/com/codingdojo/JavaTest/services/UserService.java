package com.codingdojo.JavaTest.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.JavaTest.models.Course;
import com.codingdojo.JavaTest.models.User;
import com.codingdojo.JavaTest.repository.CourseRepository;
import com.codingdojo.JavaTest.repository.UserCourseRepository;
import com.codingdojo.JavaTest.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserCourseRepository userCourseRepository;
	
	public User registerUser(User user) {
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);
        return userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    public User findUserById(Long id) {
    	Optional<User> u = userRepository.findById(id);
    	
    	if(u.isPresent()) {
            return u.get();
    	} else {
    	    return null;
    	}
    }

    public boolean authenticateUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        if(user == null) {
            return false;
        } else {
            if(BCrypt.checkpw(password, user.getPassword())) {
                return true;
            } else {
                return false;
            }
        }
    }
    @Autowired
	public UserRepository userRepo;
	@Autowired
	public CourseRepository courseRepo;
	public List<User> allUsers(){
		return userRepo.findAll();
	}
	public List<Course> allCourses(){
		return courseRepo.findAll();
	}
	public User createUser(User user) {
        return userRepo.save(user);
    }
	public Course createEvents(Course event) {
        return courseRepo.save(event);
    }
	public User findUser(Long user_id) {
		// TODO Auto-generated method stub
		return userRepo.findById(user_id).orElse(null);
	}
	public Course findCourseById(Long course_id) {
		// TODO Auto-generated method stub
		return courseRepo.findById(course_id).orElse(null);
	}
	public UserRepository getUserRepo() {
		return userRepo;
	}

	public void setUserRepo(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	public CourseRepository getEventRepo() {
		return courseRepo;
	}

	public void setEventRepo(CourseRepository courseRepo) {
		this.courseRepo = courseRepo;
	}
//	public UserRepository getUserRepo() {
//		return userRepo;
//	}
//
//	public void setUserRepo(UserRepository userRepo) {
//		this.userRepo = userRepo;
//	}
//
//	public CourseRepository getEventRepo() {
//		return eventRepo;
//	}
//
//	public void setEventRepo(CourseRepository courseRepo) {
//		this.courseRepo = courseRepo;
//	}

}
