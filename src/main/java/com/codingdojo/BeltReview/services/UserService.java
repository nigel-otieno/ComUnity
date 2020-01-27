package com.codingdojo.BeltReview.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.codingdojo.BeltReview.models.Event;
import com.codingdojo.BeltReview.models.Message;
import com.codingdojo.BeltReview.models.User;
import com.codingdojo.BeltReview.repository.EventRepository;
import com.codingdojo.BeltReview.repository.MessageRepository;
import com.codingdojo.BeltReview.repository.UserEventRepository;
import com.codingdojo.BeltReview.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserEventRepository userEventRepository;
	@Autowired
	private MessageRepository messageRepo;
	
    // register user and hash their password
    public User registerUser(User user) {
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);
        return userRepository.save(user);
    }
    
    // find user by email
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    // find user by id
    public User findUserById(Long id) {
    	Optional<User> u = userRepository.findById(id);
    	
    	if(u.isPresent()) {
            return u.get();
    	} else {
    	    return null;
    	}
    }
    
    // authenticate user
    public boolean authenticateUser(String email, String password) {
        // first find the user by email
        User user = userRepository.findByEmail(email);
        // if we can't find it by email, return false
        if(user == null) {
            return false;
        } else {
            // if the passwords match, return true, else, return false
            if(BCrypt.checkpw(password, user.getPassword())) {
                return true;
            } else {
                return false;
            }
        }
    }
//    public boolean forgotPassword(String password) {
//    	User user = userRepository.findByEmail(password);
//    	if(user == null) {
//    		return false;
//    	}
//    	else {
//    		if(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()) != null){
//    			return true;
//    		}else {
//    			return false;
//    		}
//    	}
//    }
//    public boolean authenticateEvent(String name, String network) {
//        // first find the user by email
//        Event event = userRepository.findByName(name);
//        // if we can't find it by email, return false
//        if(event == null) {
//            return false;
//        } else {
//            // if the passwords match, return true, else, return false
//            if(BCrypt.checkpw(network, event.getNetwork())) {
//                return true;
//            } else {
//                return false;
//            }
//        }
//    }
    
    @Autowired
	public UserRepository userRepo;
	@Autowired
	public EventRepository eventRepo;
	
	public List<User> allUsers(){
		return userRepo.findAll();
	}
	public List<Event> allEvents(){
		return eventRepo.findAll();
	}
	public User createUser(User user) {
        return userRepo.save(user);
    }
	public Event createEvents(Event event) {
        return eventRepo.save(event);
    }
	public User findUser(Long user_id) {
		// TODO Auto-generated method stub
		return userRepo.findById(user_id).orElse(null);
	}

	public Event findEvent(Long event_id) {
		// TODO Auto-generated method stub
		return eventRepo.findById(event_id).orElse(null);
	}

	public UserRepository getUserRepo() {
		return userRepo;
	}

	public void setUserRepo(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	public EventRepository getEventRepo() {
		return eventRepo;
	}

	public void setEventRepo(EventRepository eventRepo) {
		this.eventRepo = eventRepo;
	}

	public void saveUser(User attendee) {
		userRepo.save(attendee);
	}
	
	public Event saveEvent(@Valid Event event) {
		return this.eventRepo.save(event);
	}

	public void deleteEvent(Long event_id) {
		eventRepo.deleteById(event_id);
	}

	public Message createMessage(Message message) {
		return messageRepo.save(message);		
	}
//	public Password findByEmail(Password password) {
//		return userRepo.save(password);
//	}
	public List<Event> findByNameContaining(String search){
		return eventRepo.findByNameContaining(search);
	}
	public List<User> findUserByName(String search){
		return userRepo.findByNameContaining(search);
	}
	
    
}

