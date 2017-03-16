package be.occam.velo.domain.object;

import java.util.Date;

import be.occam.velo.CoordinateDTO;
import be.occam.velo.RideDTO;
import be.occam.velo.repository.RideEntity;


public class Ride {
	
	protected String uuid;
	protected String title;
	protected double radius;
	protected Date moment;
	protected Coordinate start;
	
	public String getUuid() {
		return uuid;
	}
	
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public Date getMoment() {
		return moment;
	}

	public void setMoment(Date moment) {
		this.moment = moment;
	}
	
	public Coordinate getStart() {
		return start;
	}

	public void setStart(Coordinate start) {
		this.start = start;
	}

public static Ride from( RideDTO f ) {
		
		Ride t
			= new Ride();
		t.setUuid( f.getUuid() );
		t.setTitle( f.getTitle() );
		t.setMoment( f.getMoment() );
		
		Coordinate start
			= new Coordinate();
		start.setLatitude( f.getStart().getLatitude() );
		start.setLongitude( f.getStart().getLongitude() );
		
		t.setStart( start );
		
		return t;
		
	}
	
	public static RideDTO dto( Ride f ) {
		
		RideDTO t
			= new RideDTO();
		
		t.setUuid( f.getUuid() );
		t.setTitle( f.getTitle() );
		t.setMoment( f.getMoment() );
		
		CoordinateDTO start
			= new CoordinateDTO();
		start.setLatitude( f.getStart().getLatitude() );
		start.setLongitude( f.getStart().getLongitude() );
		
		t.setStart( start );
		
		return t;
		
	}
	
	public static Ride from( RideEntity f ) {
		
		Ride t
			= new Ride();
		t.setUuid( f.getUuid() );
		t.setTitle( f.getTitle() );
		t.setMoment( f.getMoment() );
		
		
		Coordinate start
			= new Coordinate();
		
		start.setLatitude( f.getStartLatitude() );
		start.setLongitude( f.getStartLongitude() );
		
		t.setStart( start );
		
		return t;
		
	}
	
	public static RideEntity entity( Ride f ) {
		
		RideEntity t
			= new RideEntity();
		
		t.setUuid( f.getUuid() );
		t.setTitle( f.getTitle() );
		t.setMoment( f.getMoment() );
		t.setStartLatitude( f.getStart().getLatitude() );
		t.setStartLongitude( f.getStart().getLongitude() );
		
		return t;
		
	}
	
}
