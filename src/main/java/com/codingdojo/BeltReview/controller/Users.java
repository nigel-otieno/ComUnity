package com.codingdojo.BeltReview.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.codingdojo.BeltReview.models.Event;
import com.codingdojo.BeltReview.models.Message;
import com.codingdojo.BeltReview.models.User;
import com.codingdojo.BeltReview.services.FileStorageService;
import com.codingdojo.BeltReview.services.UserService;

@Controller
public class Users {

	@Autowired
	private FileStorageService fileStorageService;
	private final UserService userService;

	private final String[] state = { "AK", "AL", "AR", "AZ", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "IA", "ID", "IL",
			"IN", "KS", "KY", "LA", "MA", "MD", "ME", "MI", "MN", "MO", "MS", "MT", "NC", "ND", "NE", "NH", "NJ", "NM",
			"NV", "NY", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VA", "VT", "WA", "WI", "WV",
			"WY" };
	private final String[] country = { "Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra", "Angola",
			"Anguilla", "Antarctica", "Antigua and Barbuda", "Argentina", "Armenia", "Aruba", "Australia", "Austria",
			"Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin",
			"Bermuda", "Bhutan", "Bolivia", "Bosnia and Herzegowina", "Botswana", "Bouvet Island", "Brazil",
			"British Indian Ocean Territory", "Brunei Darussalam", "Bulgaria", "Burkina Faso", "Burundi", "Cambodia",
			"Cameroon", "Canada", "Cape Verde", "Cayman Islands", "Central African Republic", "Chad", "Chile", "China",
			"Christmas Island", "Cocos (Keeling) Islands", "Colombia", "Comoros", "Congo",
			"Congo, the Democratic Republic of the", "Cook Islands", "Costa Rica", "Cote d'Ivoire",
			"Croatia (Hrvatska)", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica",
			"Dominican Republic", "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea",
			"Estonia", "Ethiopia", "Falkland Islands (Malvinas)", "Faroe Islands", "Fiji", "Finland", "France",
			"France Metropolitan", "French Guiana", "French Polynesia", "French Southern Territories", "Gabon",
			"Gambia", "Georgia", "Germany", "Ghana", "Gibraltar", "Greece", "Greenland", "Grenada", "Guadeloupe",
			"Guam", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Heard and Mc Donald Islands",
			"Holy See (Vatican City State)", "Honduras", "Hong Kong", "Hungary", "Iceland", "India", "Indonesia",
			"Iran (Islamic Republic of)", "Iraq", "Ireland", "Israel", "Italy", "Jamaica", "Japan", "Jordan",
			"Kazakhstan", "Kenya", "Kiribati", "Korea, Democratic People's Republic of", "Korea, Republic of", "Kuwait",
			"Kyrgyzstan", "Lao, People's Democratic Republic", "Latvia", "Lebanon", "Lesotho", "Liberia",
			"Libyan Arab Jamahiriya", "Liechtenstein", "Lithuania", "Luxembourg", "Macau",
			"Macedonia, The Former Yugoslav Republic of", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali",
			"Malta", "Marshall Islands", "Martinique", "Mauritania", "Mauritius", "Mayotte", "Mexico",
			"Micronesia, Federated States of", "Moldova, Republic of", "Monaco", "Mongolia", "Montserrat", "Morocco",
			"Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal", "Netherlands", "Netherlands Antilles",
			"New Caledonia", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Niue", "Norfolk Island",
			"Northern Mariana Islands", "Norway", "Oman", "Pakistan", "Palau", "Panama", "Papua New Guinea", "Paraguay",
			"Peru", "Philippines", "Pitcairn", "Poland", "Portugal", "Puerto Rico", "Qatar", "Reunion", "Romania",
			"Russian Federation", "Rwanda", "Saint Kitts and Nevis", "Saint Lucia", "Saint Vincent and the Grenadines",
			"Samoa", "San Marino", "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Seychelles", "Sierra Leone",
			"Singapore", "Slovakia (Slovak Republic)", "Slovenia", "Solomon Islands", "Somalia", "South Africa",
			"South Georgia and the South Sandwich Islands", "Spain", "Sri Lanka", "St. Helena",
			"St. Pierre and Miquelon", "Sudan", "Suriname", "Svalbard and Jan Mayen Islands", "Swaziland", "Sweden",
			"Switzerland", "Syrian Arab Republic", "Taiwan, Province of China", "Tajikistan",
			"Tanzania, United Republic of", "Thailand", "Togo", "Tokelau", "Tonga", "Trinidad and Tobago", "Tunisia",
			"Turkey", "Turkmenistan", "Turks and Caicos Islands", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates",
			"United Kingdom", "United States", "United States Minor Outlying Islands", "Uruguay", "Uzbekistan",
			"Vanuatu", "Venezuela", "Vietnam", "Virgin Islands (British)", "Virgin Islands (U.S.)",
			"Wallis and Futuna Islands", "Western Sahara", "Yemen", "Yugoslavia", "Zambia", "Zimbabwe" };

	public Users(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping("/")
	public String Login(@ModelAttribute("user") User user, Model model) {
		model.addAttribute("state", state);
		model.addAttribute("country", country);
		return "Login.jsp";
	}

	@RequestMapping("/register")
	public String Registration(@ModelAttribute("user") User user, Model model) {
		model.addAttribute("state", state);
		model.addAttribute("country", country);
		return "Registration.jsp";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
		if (result.hasErrors()) {
			return "Registration.jsp";
		}
		User u = userService.registerUser(user);
		session.setAttribute("user_id", u.getId());
		this.userService.saveUser(user);
		return "redirect:/timeline";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginUser(@Valid @RequestParam("email") String email, @ModelAttribute("user") User user,
			@RequestParam("password") String password, Model model, HttpSession session) {
		boolean isAuthenticated = userService.authenticateUser(email, password);
		if (isAuthenticated) {
			User u = userService.findByEmail(email);
			session.setAttribute("user_id", u.getId());
			return "redirect:/timeline";
		} else {
			model.addAttribute("error", "Invalid Credentials. Try again.");
			return "Login.jsp";
		}
	}

	@RequestMapping("/create")
	public String showcreator(HttpSession session, @ModelAttribute("user") User user, Model model,
			@ModelAttribute("event") Event event) {
		Long userId = (Long) session.getAttribute("user_id");
		User u = userService.findUserById(userId);
		model.addAttribute("user_logged", u);
		model.addAttribute("country", country);
		model.addAttribute("state", state);
		List<User> users = userService.allUsers();
		model.addAttribute("users", users);
		model.addAttribute("events", userService.allEvents());
		return "createvent.jsp";
	}

	@RequestMapping(value = "/event/create", method = RequestMethod.POST)
	public String showCreator(@Valid @ModelAttribute("event") Event event, BindingResult result, HttpSession session,
			@RequestParam("file") MultipartFile file) {
		if (result.hasErrors()) {
			return "eventcreator.jsp";
		} else {
			Long userId = (Long) session.getAttribute("user_id");
			User user = userService.findUserById(userId);
			String fileName = fileStorageService.storeEventFile(file);
			event.setUser(user);
			event.setFileName(fileName);
			userService.createEvents(event);
			return "redirect:/timeline";
		}
	}

	@RequestMapping("/timeline")
	public String timeline(HttpSession session, Model model, @ModelAttribute("event") Event event,
			@ModelAttribute("message") Message message) {
		Long userId = (Long) session.getAttribute("user_id");
		User u = userService.findUserById(userId);
		model.addAttribute("user_logged", u);
		model.addAttribute("country", country);
		model.addAttribute("state", state);
		List<User> users = userService.allUsers();
		model.addAttribute("users", users);
		model.addAttribute("events", userService.allEvents());
		return "timeline.jsp";
		// get user from session, save them in the model and return the home page
	}

	@GetMapping("/search")
	public String search(@RequestParam("event") String event, Model model) {
		System.out.println(event);
		return "redirect:/search/" + event;
	}

	@GetMapping("/search/{event_name}")
	public String searchEvent(@PathVariable("event_name") String event, Model model) {
		List<Event> Name = userService.findByNameContaining(event);
		// System.out.println(Name);
		model.addAttribute("events", Name);
		return "timeline2.jsp";
	}

	@PostMapping("/searching")
	public String searchUser(@RequestParam("user") String user, Model model) {
		System.out.println(user);
		return "redirect:/search_user/" + user;
	}

	@GetMapping("/search_user/{user_name}")
	public String searchResults(@PathVariable("user_name") String user, Model model) {
		List<User> User_Name = userService.findUserByName(user);
		System.out.println(User_Name);
		model.addAttribute("users", User_Name);
		return "other_profile.jsp";
	}

	@RequestMapping(value = "/events", method = RequestMethod.POST)
	public String eventsCreator(@Valid @ModelAttribute("event") Event event, BindingResult result,
			HttpSession session) {
		if (result.hasErrors()) {
			return "timeline.jsp";
		} else {
			Long userId = (Long) session.getAttribute("user_id");
			User user = userService.findUserById(userId);
			event.setUser(user);
			userService.createEvents(event);
			return "redirect:/timeline";
		}
	}

	@RequestMapping(value = "/events/{event_id}/comment", method = RequestMethod.POST)
	public String comment(@Valid @ModelAttribute("message") Message message, @PathVariable("event_id") Long event_id,
			BindingResult result, HttpSession session) {
		if (result.hasErrors()) {
			return "Event-Message.jsp";
		} else {
			Long userId = (Long) session.getAttribute("user_id");
			User user = userService.findUserById(userId);
			Event event = userService.findEvent(event_id);
			message.setUser(user);
			message.setEvent(event);
			userService.createMessage(message);
			return "redirect:/events/" + event.getId();
		}
	}

	@RequestMapping("/events/{event_id}")
	public String eventList(HttpSession session, @ModelAttribute("message") Message message,
			@PathVariable("event_id") Long event_id, Model model) {
		Event event = userService.findEvent(event_id);
		model.addAttribute("event", event);
		Long userId = (Long) session.getAttribute("user_id");
		User u = userService.findUserById(userId);
		model.addAttribute("user_logged", u);
		model.addAttribute("country", country);
		model.addAttribute("state", state);
		List<User> users = userService.allUsers();
		model.addAttribute("users", users);
		model.addAttribute("events", userService.allEvents());
		return "Event-Message.jsp";
	}

	@RequestMapping("/events/{event_id}/join")
	public String addJoin(@PathVariable("event_id") Long event_id, HttpSession session) {
		Long userId = (Long) session.getAttribute("user_id");
		User attendee = userService.findUserById(userId);
		Event eventsAttending = userService.findEvent(event_id);
		List<User> attendees = eventsAttending.getAttendees();
		attendees.add(attendee);
		eventsAttending.setAttendees(attendees);
		userService.saveUser(attendee);
		return "redirect:/timeline";
	}

	@RequestMapping("/events/{event_id}/cancel")
	public String cancel(@PathVariable("event_id") Long event_id, HttpSession session) {
		Long userId = (Long) session.getAttribute("user_id");
		User attendee = userService.findUserById(userId);
		Event eventsAttending = userService.findEvent(event_id);
		List<User> attendees = eventsAttending.getAttendees();
		attendees.remove(attendee);
		eventsAttending.setAttendees(attendees);
		userService.saveUser(attendee);
		return "redirect:/timeline";
	}

	@RequestMapping("/events/{event_id}/edit")
	public String edit(@PathVariable("event_id") Long event_id, Model model) {
		Event event = userService.findEvent(event_id);
		model.addAttribute("event", event);
		model.addAttribute("country", country);
		model.addAttribute("state", state);
		return "eventEdit.jsp";
	}

	@PutMapping("/events/{id}/update")
	public String updateEvent(@PathVariable("id") Long id, Model model, @Valid @ModelAttribute("event") Event event,
			BindingResult result, @RequestParam("file") MultipartFile file) {
		if (result.hasErrors()) {
			model.addAttribute("country", country);
			model.addAttribute("state", state);
			return "eventEdit.jsp";
		} else {
			String fileName = fileStorageService.storeEventFile(file);
			event.setFileName(fileName);
			this.userService.saveEvent(event);
			return "redirect:/events/" + event.getId();
		}
	}

	@RequestMapping("/events/{event_id}/delete")
	public String delete(@PathVariable("event_id") Long event_id, Model model) {
		userService.deleteEvent(event_id);
		return "redirect:/timeline";
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

	@RequestMapping("/messages")
	public String messages(HttpSession session) {
		Long userId = (Long) session.getAttribute("user_id");
		return "messages.jsp";
	}
	@RequestMapping("/contactus")
	public String ContactUs(HttpSession session) {
		Long userId = (Long) session.getAttribute("user_id");
		return "contactus.jsp";
	}
	@RequestMapping("/profile/{user_id}")
	public String profile(HttpSession session, @PathVariable("user_id") Long user_id, Model model) {
		Long userId = (Long) session.getAttribute("user_id");
		User u = userService.findUserById(userId);
		model.addAttribute("user_logged", u);
		return "profile.jsp";
	}

	@RequestMapping("/user/{user_id}/edit")
	public String editprofile(HttpSession session, @PathVariable("user_id") Long user_id, Model model) {
		Long userId = (Long) session.getAttribute("user_id");
		User u = userService.findUserById(userId);
		model.addAttribute("user_logged", u);
		return "editprofile.jsp";
	}

	@PutMapping("/user/{id}/update")
	public String updateProfile(@PathVariable("id") Long id, Model model, @Valid @ModelAttribute("user") User user,
			BindingResult result) {
		if (result.hasErrors()) {
			return "editprofile.jsp";
		} else {
			this.userService.saveUser(user);
			return "redirect:/profile/" + user.getId();
		}
	}

	@RequestMapping("/terms_conditions")
	public String terms_conditions(HttpSession session, Model model) {
		return "terms_conditions.jsp";
	}

}
