package be.occam.velo.domain.object;

import java.util.Date;

import be.occam.velo.LocationDTO;
import be.occam.velo.repository.LocationEntity;

public class Location {
	
	protected String uuid;
	protected String userID;
	protected double longitude;
	protected double lattitude;
	protected Date moment;
	
	public String getUuid() {
		return uuid;
	}
	
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	public String getUserID() {
		return userID;
	}
	
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	public double getLongitude() {
		return longitude;
	}
	
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public double getLattitude() {
		return lattitude;
	}
	public void setLattitude(double lattitude) {
		this.lattitude = lattitude;
	}
	public Date getMoment() {
		return moment;
	}
	public void setMoment(Date moment) {
		this.moment = moment;
	}

	public static Location from( LocationDTO f ) {
		
		Location t
			= new Location();
		t.setUuid( f.getUuid() );
		t.setUserID( f.getUserID() );
		t.setLattitude( f.getLattitude() );
		t.setLongitude( f.getLongitude() );
		t.setMoment( f.getMoment() );
		
		return t;
		
	}
	
	public static LocationDTO dto( Location f ) {
		
		LocationDTO t
			= new LocationDTO();
		
		t.setUuid( f.getUuid() );
		t.setUserID( f.getUserID() );
		t.setLattitude( f.getLattitude() );
		t.setLongitude( f.getLongitude() );
		t.setMoment( f.getMoment() );
		
		return t;
		
	}
	
	public static Location from( LocationEntity f ) {
		
		Location t
			= new Location();
		t.setUuid( f.getUuid() );
		t.setUserID( f.getUserID() );
		t.setLattitude( f.getLattitude() );
		t.setLongitude( f.getLongitude() );
		t.setMoment( f.getMoment() );
		
		return t;
		
	}
	
	public static LocationEntity entity( Location f ) {
		
		LocationEntity t
			= new LocationEntity();
		
		t.setUuid( f.getUuid() );
		t.setUserID( f.getUserID() );
		t.setLattitude( f.getLattitude() );
		t.setLongitude( f.getLongitude() );
		t.setMoment( f.getMoment() );
		
		return t;
		
	}
	

}
