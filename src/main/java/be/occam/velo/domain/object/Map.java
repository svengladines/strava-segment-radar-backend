package be.occam.velo.domain.object;

import be.occam.velo.MapDTO;
import be.occam.velo.repository.MapEntity;


public class Map {
	
	protected String uuid;
	protected String userID;
	protected double radius;
	
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
	public double getRadius() {
		return radius;
	}
	public void setRadius(double radius) {
		this.radius = radius;
	}
	
	public static MapDTO dto( Map f ) {
		
		MapDTO t
			= new MapDTO();
		
		t.setRadius( f.getRadius() );
		t.setUserID( f.getUserID() );
		t.setUuid( f.getUuid() );
		
		return t;
		
		
	}
	
	public static Map from( MapDTO f ) {
		
		Map t
			= new Map();
		
		t.setRadius( f.getRadius() );
		t.setUserID( f.getUserID() );
		t.setUuid( f.getUuid() );
		
		return t;
		
		
	}
	
	public static MapEntity entity( Map f ) {
		
		MapEntity t
			= new MapEntity();
		
		t.setRadius( f.getRadius() );
		t.setUserID( f.getUserID() );
		t.setUuid( f.getUuid() );
		
		return t;
		
		
	}
	
	public static Map from( MapEntity f ) {
		
		Map t
			= new Map();
		
		t.setRadius( f.getRadius() );
		t.setUserID( f.getUserID() );
		t.setUuid( f.getUuid() );
		
		return t;
		
		
	}
	

}
