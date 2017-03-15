package be.occam.velo.domain.object;

import java.util.Date;

import be.occam.velo.RideDTO;
import be.occam.velo.repository.RideEntity;


public class Ride {
	
	protected String uuid;
	protected String title;
	protected double radius;
	protected Date moment;
	
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
	
public static Ride from( RideDTO f ) {
		
		Ride t
			= new Ride();
		t.setUuid( f.getUuid() );
		t.setTitle( f.getTitle() );
		t.setMoment( f.getMoment() );
		
		return t;
		
	}
	
	public static RideDTO dto( Ride f ) {
		
		RideDTO t
			= new RideDTO();
		
		t.setUuid( f.getUuid() );
		t.setTitle( f.getTitle() );
		t.setMoment( f.getMoment() );
		
		return t;
		
	}
	
	public static Ride from( RideEntity f ) {
		
		Ride t
			= new Ride();
		t.setUuid( f.getUuid() );
		t.setTitle( f.getTitle() );
		t.setMoment( f.getMoment() );
		
		return t;
		
	}
	
	public static RideEntity entity( Ride f ) {
		
		RideEntity t
			= new RideEntity();
		
		t.setUuid( f.getUuid() );
		t.setTitle( f.getTitle() );
		t.setMoment( f.getMoment() );
		
		return t;
		
	}
	
}
