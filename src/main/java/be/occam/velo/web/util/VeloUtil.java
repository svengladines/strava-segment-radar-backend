package be.occam.velo.web.util;

import be.occam.velo.domain.object.Location;

public class VeloUtil {
	
	public static String color( Location location ) {
		
		String color 
			= "#000000";
		
		if ( "reini".equals( location.getRiderID() ) ) {
			color = "#ff0000";
		}
		else if ( "krikke".equals( location.getRiderID() ) ) {
			color = "#00ff00";
		}
		else if ( "john".equals( location.getRiderID() ) ) {
			color = "#0000ff";
		}
		else if ( "svekke".equals( location.getRiderID() ) ) {
			color = "#ffffff";
		}
		
		return color;
		
	}
	
	public static String DUVELKE_START_TITLE = "Dag Feestvarken!";

}
