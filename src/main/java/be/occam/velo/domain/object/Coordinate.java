package be.occam.velo.domain.object;

import java.util.Date;

import be.occam.velo.CoordinateDTO;

public class Coordinate {
	
	protected double longitude;
	protected double latitude;
	protected Date moment;
	
	public double getLongitude() {
		return longitude;
	}
	
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public Date getMoment() {
		return moment;
	}
	public void setMoment(Date moment) {
		this.moment = moment;
	}

	public static Coordinate from( CoordinateDTO f ) {
		
		Coordinate t
			= new Coordinate();
		
		t.setLatitude( f.getLatitude() );
		t.setLongitude( f.getLongitude() );
		
		return t;
		
	}
	
	public static CoordinateDTO dto( Coordinate f ) {
		
		CoordinateDTO t
			= new CoordinateDTO();
		
		t.setLatitude( f.getLatitude() );
		t.setLongitude( f.getLongitude() );
		
		return t;
		
	}
	

}
