package com.codingdojo.JavaTest.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.codingdojo.JavaTest.models.Course;
import com.codingdojo.JavaTest.models.User;
import com.codingdojo.JavaTest.services.UserService;

@Controller
public class UserController {
private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	@RequestMapping("/")
    public String LogReg(@ModelAttribute("user") User user) {
    	return "LoginRegistration.jsp";
    }
  
    @RequestMapping(value="/register", method=RequestMethod.POST)
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
    	if(result.hasErrors()) {
    		return "LoginRegistration.jsp";
    	}
    	User u = userService.registerUser(user);
    	session.setAttribute("user_id", u.getId());
    	return "redirect:/courses";
    }
    
    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String loginUser(@RequestParam("email") String email, @ModelAttribute("user") User user, @RequestParam("password") String password, Model model, HttpSession session) {
    	boolean isAuthenticated = userService.authenticateUser(email, password);
    	if(isAuthenticated) {
    		User u = userService.findByEmail(email);
        	session.setAttribute("user_id", u.getId());
    		return "redirect:/courses";
    	}else {
    		model.addAttribute("error", "Invalid Credentials. Try again.");
    		return "LoginRegistration.jsp";
    	}
    }
    @RequestMapping("/courses")
    public String dashboard(HttpSession session, Model model, @ModelAttribute("event") Course course) {
    	Long userId = (Long) session.getAttribute("user_id");
    	User u = userService.findUserById(userId);
    	model.addAttribute("user_logged", u);
		List<User> users = userService.allUsers();
	    model.addAttribute("users", users);
	    model.addAttribute("courses", userService.allCourses());
    	return "Dashboard.jsp";
    }
    @RequestMapping("/courses/new")
    public String createCourse(@ModelAttribute("course") Course course) {
    	return "CoursesNew.jsp";
    }
    @RequestMapping(value="/courses", method=RequestMethod.POST)
	public String eventsCreator(@Valid @ModelAttribute("course") Course course, BindingResult result, HttpSession session) {
		if(result.hasErrors()) {
			return "Dashboard.jsp";
		}else {
			Long userId = (Long) session.getAttribute("user_id");
			User user = userService.findUserById(userId);
			course.setUser(user);
			userService.createEvents(course);
			return "redirect:/courses";
		}
	}
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
    @RequestMapping("/courses/{course_id}")
    public String courseShow(@PathVariable("course_id") Long course_id, Model model) {
    	Course course = userService.findCourseById(course_id);
    	model.addAttribute("event", course);
    	return "courseShow.jsp";
    }
}
