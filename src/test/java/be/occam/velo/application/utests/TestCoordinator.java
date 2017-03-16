package be.occam.velo.application.utests;

import org.junit.Test;

import be.occam.velo.domain.people.Coordinator;

public class TestCoordinator {
	
	@Test
	public void doesItSmoke() {
		
		double brxLat = 50.84667D;
		double brxLong = 4.35472;
		
		double snaLat = 43.333333D;
		double snaLong = 11.333333;
		
		double distance
			= new Coordinator().distance( brxLat, brxLong, snaLat, snaLong );
		
		System.out.println( String.format( "distance is %s kilometers", distance ) );
		
	}

}
