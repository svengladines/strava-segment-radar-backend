package be.occam.velo.domain.object;

import static be.occam.utils.javax.Utils.*;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import be.occam.velo.CoordinateDTO;
import be.occam.velo.RideDTO;
import be.occam.velo.repository.RideEntity;


public class Ride {
	
	public static enum Status {
		
		READY_TO_ROLL, ROLLIN_IN_THE_DEEP, PAUZED, FINITO
		
	};
	
	protected String uuid;
	protected String title;
	protected double radius;
	protected Date moment;
	protected Coordinate start;
	protected Status status;
	protected List<String> riders;
	
	public Ride() {
		this.riders = list();
	}
	
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
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	public List<String> getRiders() {
		return riders;
	}

	public static Ride from( RideDTO f ) {
		
		Ride t
			= new Ride();
		
		t.setUuid( f.getUuid() );
		t.setTitle( f.getTitle() );
		t.setMoment( f.getMoment() );
		
		if ( f.getStart() != null ) {
		
			Coordinate start
				= new Coordinate();
			
			start.setLatitude( f.getStart().getLatitude() );
			start.setLongitude( f.getStart().getLongitude() );
			
			t.setStart( start );
			
		}
		
		if ( f.getStatus() != null ) {
		
			t.setStatus( Ride.Status.valueOf( f.getStatus().name() ) );
			
		}
		
		return t;
		
	}
	
	public static RideDTO dto( Ride f ) {
		
		RideDTO t
			= new RideDTO();
		
		t.setUuid( f.getUuid() );
		t.setTitle( f.getTitle() );
		t.setMoment( f.getMoment() );
		
		if ( f.getStart() != null ) {
			CoordinateDTO start
				= new CoordinateDTO();
			start.setLatitude( f.getStart().getLatitude() );
			start.setLongitude( f.getStart().getLongitude() );
			t.setStart( start );
		}
		
		if ( f.getStatus() != null ) {
		
			t.setStatus( RideDTO.Status.valueOf( f.getStatus().name() ) );
			
		}
		
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
		
		if ( f.getStatus() != null ) {
		
			t.setStatus( Status.valueOf( f.getStatus() ) );
			
		}
		
		if ( ! isEmpty( f.getRiders() ) ) {
			
			StringTokenizer tok
				= new StringTokenizer( f.getRiders(), "," );
			
			while ( tok.hasMoreTokens() ) {
				t.getRiders().add( tok.nextToken() );
			}
			
			
		}
		
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
		t.setStatus( f.getStatus().name() );
		
		return t;
		
	}
	
}
