package be.occam.velo.web.dto;

import java.util.Date;

public class LocationDTO {
	
	protected String uuid;
	protected String riderID;
	protected String rideID;
	protected double longitude;
	protected double latitude;
	protected Date moment;
	protected String color;
	protected String event;
	protected String comment;
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getUserID() {
		return riderID;
	}
	public LocationDTO setUserID(String userID) {
		this.riderID = userID;
		return this;
	}
	
	public double getLongitude() {
		return longitude;
	}
	
	public LocationDTO setLongitude(double longitude) {
		this.longitude = longitude;
		return this;
	}
	
	public double getLattitude() {
		return latitude;
	}
	
	public LocationDTO setLattitude(double lattitude) {
		this.latitude = lattitude;
		return this;
	}
	
	public Date getMoment() {
		return moment;
	}
	
	public LocationDTO setMoment(Date moment) {
		this.moment = moment;
		return this;
	}
	
	public String getRiderID() {
		return riderID;
	}
	
	public void setRiderID(String riderID) {
		this.riderID = riderID;
	}
	
	public String getRideID() {
		return rideID;
	}
	
	public void setRideID(String rideID) {
		this.rideID = rideID;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
