package be.occam.velo.domain.object;

import static be.occam.utils.javax.Utils.isEmpty;

import java.util.Date;

import be.occam.velo.repository.LocationEntity;
import be.occam.velo.web.dto.LocationDTO;

public class Location {
	
	protected String uuid;
	protected String rideID;
	protected String riderID;
	protected double longitude;
	protected double latitude;
	protected Date moment;
	protected String comment;
	protected Event event;
	
	public String getUuid() {
		return uuid;
	}
	
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	public String getRideID() {
		return rideID;
	}

	public void setRideID(String rideID) {
		this.rideID = rideID;
	}

	public String getRiderID() {
		return riderID;
	}

	public void setRiderID(String riderID) {
		this.riderID = riderID;
	}

	public double getLongitude() {
		return longitude;
	}
	
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public double getLattitude() {
		return latitude;
	}
	public void setLattitude(double lattitude) {
		this.latitude = lattitude;
	}
	public Date getMoment() {
		return moment;
	}
	
	public void setMoment(Date moment) {
		this.moment = moment;
	}
	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public static Location from( LocationDTO f ) {
		
		Location t
			= new Location();
		t.setUuid( f.getUuid() );
		t.setRideID( f.getRideID() );
		t.setRiderID( f.getRiderID() );
		t.setLattitude( f.getLattitude() );
		t.setLongitude( f.getLongitude() );
		t.setMoment( f.getMoment() );
		
		if ( ! isEmpty( f.getComment() ) ) {
			t.setComment( f.getComment() );
		}
		
		if ( f.getEvent() != null ) {
			t.setEvent( Event.valueOf( f.getEvent() ) );
		}
		
		return t;
		
	}
	
	public static LocationDTO dto( Location f ) {
		
		LocationDTO t
			= new LocationDTO();
		
		t.setUuid( f.getUuid() );
		t.setRideID( f.getRideID() );
		t.setRiderID( f.getRiderID() );
		t.setLattitude( f.getLattitude() );
		t.setLongitude( f.getLongitude() );
		t.setMoment( f.getMoment() );
		
		return t;
		
	}
	
	public static Location from( LocationEntity f ) {
		
		Location t
			= new Location();
		t.setUuid( f.getUuid() );
		t.setRideID( f.getRideID() );
		t.setRiderID( f.getRiderID() );
		t.setLattitude( f.getLattitude() );
		t.setLongitude( f.getLongitude() );
		t.setMoment( f.getMoment() );
		
		return t;
		
	}
	
	public static LocationEntity entity( Location f ) {
		
		LocationEntity t
			= new LocationEntity();
		
		t.setUuid( f.getUuid() );
		t.setRideID( f.getRideID() );
		t.setRiderID( f.getRiderID() );
		t.setLattitude( f.getLattitude() );
		t.setLongitude( f.getLongitude() );
		t.setMoment( f.getMoment() );
		
		return t;
		
	}
	

}
