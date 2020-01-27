package com.codingdojo.BeltReview.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="user_event")
public class UserEvent {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    @Column(updatable=false)
	    @DateTimeFormat(pattern="yyyy-MM-dd")
	    private Date createdAt;
	    @DateTimeFormat(pattern="yyyy-MM-dd")
	    private Date updatedAt;
	   
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name="user_id")
	    private User attendee;
	    
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name="event_id")
	    private Event eventAttending;
	    
	    public UserEvent() {
	        
	    }

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Date getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(Date createdAt) {
			this.createdAt = createdAt;
		}

		public Date getUpdatedAt() {
			return updatedAt;
		}

		public void setUpdatedAt(Date updatedAt) {
			this.updatedAt = updatedAt;
		}

		public User getAttendee() {
			return attendee;
		}

		public void setAttendee(User attendee) {
			this.attendee = attendee;
		}

		public Event getEventAttending() {
			return eventAttending;
		}

		public void setEventAttending(Event eventAttending) {
			this.eventAttending = eventAttending;
		}
//		public UserEvent joinUserEvents(UserEvent attendees) {
//			return eventAttending;
//		}
	    
}
	
